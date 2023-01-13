package com.nimbleways.jidokabot.services.implementations;

import com.nimbleways.jidokabot.dto.useractiondto.UserActionDetailsDTO;
import com.nimbleways.jidokabot.entities.*;
import com.nimbleways.jidokabot.events.StartCountingEventPublisher;
import com.nimbleways.jidokabot.repositories.BoardRepository;
import com.nimbleways.jidokabot.repositories.RuleRepository;
import com.nimbleways.jidokabot.repositories.UserRepository;
import com.nimbleways.jidokabot.repositories.implementations.TrelloRepository;
import com.nimbleways.jidokabot.scheduler.TimeTasksManager;
import com.nimbleways.jidokabot.services.IMessageService;
import com.nimbleways.jidokabot.services.IProjectManagementService;
import com.nimbleways.jidokabot.services.ITrelloWebhookService;
import com.nimbleways.jidokabot.utils.RestTemplateUtils;
import com.nimbleways.jidokabot.utils.TimeUnitConverterUtils;
import com.nimbleways.jidokabot.utils.messageformatters.IMessageFormatter;
import com.slack.api.methods.SlackApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class WebhookServiceImpl implements ITrelloWebhookService {
    private final StartCountingEventPublisher startCountingEventPublisher;
    private final RuleRepository ruleRepository;
    private final static String WEBHOOK_URL = "https://api.trello.com/1/webhooks/";
    private final RestTemplateUtils restTemplateUtils;
    private final TimeTasksManager timeTasksManager;
    private final static String WEBHOOK_CALLBACK = "http://cb0d-41-140-137-99.ngrok.io/api/webhooks/new";
    private final IProjectManagementService projectManagementService;
    private final IMessageService messageService;
    private final IMessageFormatter messageFormatter;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Override
    public void handleUserAction(final UserActionDetailsDTO userActionDetails) throws SlackApiException, IOException {

        switch (userActionDetails.getAction().getDisplay().getTranslationKey()) {
            case "action_move_card_from_list_to_list":{
                handleUsTimeRule(userActionDetails);
                handleStockRule(userActionDetails);
                break;
            }
            default: {

            }
        }
    }

    @Override
    public Webhook createWebHook(final String idBoard,final String userId) {
        final User user = userRepository.getById(userId);
        final String url = WEBHOOK_URL + "?callbackURL=" + WEBHOOK_CALLBACK + "&idModel=" + idBoard + "&key=" + TrelloRepository.APPLICATION_KEY + "&token=" + user.getToken();
        return restTemplateUtils.postWebhook(url, null);
    }

    public void handleUsTimeRule(final UserActionDetailsDTO userActionDetails) {
        final String currentColumn = userActionDetails.getAction().getDisplay().getEntities().getListAfter().getText();
        //cancel any other time rule applyes to the current column
        final String cardId = userActionDetails.getAction().getDisplay().getEntities().getCard().getId();
        timeTasksManager.cancelTimeTask(cardId);
        //current column
        //get time rule applyed to the current column
        final String boardId = userActionDetails.getModel().getId();
        final Board board = boardRepository.getById(boardId);
        final List<UsTimeRule> timeRulesActives = ruleRepository.getActiveTimeRulesByBoardAndTypeAndColumnName(board, "time", currentColumn);
        if(timeRulesActives.isEmpty()){
            return ;
        }
            //there is only one rule (configured in addRule method)
            final UsTimeRule ruleToApply = timeRulesActives.get(0);
            int leadTime = (int) ruleToApply.getDuration();
            if (Objects.equals(ruleToApply.getUnit(), "h")) {
                leadTime = TimeUnitConverterUtils.hoursToMills(leadTime);
            }
            else if (Objects.equals(ruleToApply.getUnit(), "m")) {
                leadTime = TimeUnitConverterUtils.minutesToMills(leadTime);
            }
            //format message
            final String cardName = userActionDetails.getAction().getDisplay().getEntities().getCard().getText();
            final String shortLink = userActionDetails.getAction().getDisplay().getEntities().getCard().getShortLink();
            final String message = ruleToApply.getMessage();
            final String ownerName = userActionDetails.getAction().getDisplay().getEntities().getMemberCreator().getText();
            final String unit = "m".equals(ruleToApply.getUnit()) ? "minute(s)" :"hour(s)";
            final String timeSpent = (int) ruleToApply.getDuration()+" "+unit;
            final String columnName = ruleToApply.getTimeRuleColumnName();
            final String cardLink = "https://trello.com/c/"+shortLink;
            final Map<String,String> params = Map.of("Cardname","\""+cardName+"\"","timespent",timeSpent,"ownername",ownerName,"columnname",columnName,"cardlink",cardLink);
            final String messageToSend = messageFormatter.formatMessage(message,params);
            //publish new event to calcul time
            startCountingEventPublisher.publishStartCountingEvent(cardId,leadTime, messageToSend,ruleToApply.getChannel());
    }
    public void handleStockRule(final UserActionDetailsDTO userActionDetails) throws SlackApiException, IOException {
        final String currentColumn = userActionDetails.getAction().getDisplay().getEntities().getListAfter().getText();
        //get stock active rules applied to the current column
        final String boardId = userActionDetails.getModel().getId();
        final Board board = boardRepository.getById(boardId);
        //find number of cards existing in the current column
        final List<StockRule> stockRulesActives = ruleRepository.getActiveStockRulesByBoardAndTypeAndColumnName(board, "stock", currentColumn);
        if(stockRulesActives.isEmpty()){
            return;
        }
        final String columnId = userActionDetails.getAction().getDisplay().getEntities().getListAfter().getId();
        //there is one stock rule by column
        final StockRule stockRuleToApply = stockRulesActives.get(0);
        final int maximumCardsPossible = (int) stockRuleToApply.getNbCards();
        //gte nb of cards in the current column
        final int nbOfCardsInTheCurrentColumn =  projectManagementService.getCardsOfTrelloList(columnId,board.getOwner().getId() ).size();
        if(nbOfCardsInTheCurrentColumn>maximumCardsPossible){
            //post message to Slack
            final String channel = stockRuleToApply.getChannel();
            final String message = stockRuleToApply.getMessage();
            final String ownerName = userActionDetails.getAction().getDisplay().getEntities().getMemberCreator().getText();
            final Map<String,String> params = new HashMap<>();
            params.put("cardnumber",String.valueOf((int) stockRuleToApply.getNbCards()));
            params.put("ownername",ownerName);
            params.put("columnname",currentColumn);
            final String messageToSend = messageFormatter.formatMessage(message,params);
            messageService.sendSlackMessage(messageToSend,channel);
        }
    }
}
