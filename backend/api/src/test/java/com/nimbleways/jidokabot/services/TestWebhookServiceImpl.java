package com.nimbleways.jidokabot.services;

import com.nimbleways.jidokabot.configuration.RestTemplateConfiguration;
import com.nimbleways.jidokabot.dto.useractiondto.*;
import com.nimbleways.jidokabot.entities.*;
import com.nimbleways.jidokabot.events.StartCountingEventPublisher;
import com.nimbleways.jidokabot.repositories.BoardRepository;
import com.nimbleways.jidokabot.repositories.RuleRepository;
import com.nimbleways.jidokabot.repositories.UserRepository;
import com.nimbleways.jidokabot.repositories.implementations.SlackMessageRepository;
import com.nimbleways.jidokabot.repositories.implementations.TrelloRepository;
import com.nimbleways.jidokabot.scheduler.TimeTasksManager;
import com.nimbleways.jidokabot.services.implementations.SlackMessageService;
import com.nimbleways.jidokabot.services.implementations.TrelloServiceImpl;
import com.nimbleways.jidokabot.services.implementations.WebhookServiceImpl;
import com.nimbleways.jidokabot.utils.RestTemplateUtils;
import com.nimbleways.jidokabot.utils.messageformatters.IMessageFormatter;
import com.nimbleways.jidokabot.utils.messageformatters.MessageFormater;
import com.slack.api.methods.SlackApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
@Import({MessageFormater.class,RestTemplateConfiguration.class, SlackMessageService.class, SlackMessageRepository.class, TrelloServiceImpl.class, TrelloRepository.class,RestTemplateUtils.class})
@SpringBootTest
public class TestWebhookServiceImpl {

    @Mock
    StartCountingEventPublisher startCountingEventPublisher ;
    @Mock
    RestTemplateUtils restTemplateUtils;
    @Mock
    RuleRepository ruleRepository ;
    @Mock
    TimeTasksManager timeTasksManager;
    @Mock
    IMessageFormatter messageFormatter;
    @Mock
    BoardRepository boardRepository;
    @Mock
    IMessageService messageService;
    @Mock
    IProjectManagementService projectManagementService;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    WebhookServiceImpl webhookService;

    @Test
    public void testCreateWebhook(){
        //GIVEN
        String boardId = UUID.randomUUID().toString();
        Webhook expected = new Webhook();
        String userId = UUID.randomUUID().toString();
        //WHEN
        doReturn(new User()).when(userRepository).getById(userId);
        doReturn(expected).when(restTemplateUtils).postWebhook(anyString(),any());
        Webhook actual =  webhookService.createWebHook(boardId, userId);
        assertEquals(expected,actual);
    }

    private UserActionDetailsDTO createUserActionDatailsDtoExample(){
        UserActionDetailsDTO userActionDetails = new UserActionDetailsDTO();
        String uuid  = UUID.randomUUID().toString();
        ModelDTO model = new ModelDTO(uuid,"board1");
        userActionDetails.setModel(model);
        UserActionDTO userAction = new UserActionDTO();
        DisplayActionDTO displayAction = new DisplayActionDTO();
        displayAction.setTranslationKey("action_move_card_from_list_to_list");
        ActionEntitiesDTO actionEntities = new ActionEntitiesDTO(new TrelloListDTO(null,"idList","Doing"),new CardDTO("type","idList","cardId","text","shortLink"),new MemberCreatorDTO("id","username","Nom et prenom"));
        displayAction.setEntities(actionEntities) ;
        userAction.setDisplay(displayAction);
        userActionDetails.setAction(userAction);
        return userActionDetails;
    }

    @Test
    public void testHandleUserAction() throws SlackApiException, IOException {
        testHandleUsTimeRule();
        testhandleStockRule();
    }


    public void testHandleUsTimeRule() throws SlackApiException, IOException {
        //GIVEN
        final UserActionDetailsDTO userActionDetails = createUserActionDatailsDtoExample();
        //create timeRule
        final UsTimeRule usTimeRule = new UsTimeRule("Doing",4,"h");
        usTimeRule.setChannel("projet");
        usTimeRule.setMessage("test message");
        List<UsTimeRule> timeRules = Arrays.asList(usTimeRule) ;
        final  String messageToSend = "test message";
        final Board board = new Board();
        final User owner = new User();
        owner.setId(UUID.randomUUID().toString());
        board.setOwner(owner);
        //WHEN
        doReturn(board).when(boardRepository).getById(anyString());
        doReturn(timeRules).when(ruleRepository).getActiveTimeRulesByBoardAndTypeAndColumnName(any(Board.class),eq("time"),eq("Doing"));
        doNothing().when(startCountingEventPublisher).publishStartCountingEvent(anyString(),anyInt(),anyString(),anyString());
        doReturn(messageToSend).when(messageFormatter).formatMessage(anyString(),any());
        //THEN
        webhookService.handleUsTimeRule(userActionDetails);
        verify(startCountingEventPublisher,times(1)).publishStartCountingEvent("cardId",(int) timeRules.get(0).getDuration()*3600*1000,messageToSend,timeRules.get(0).getChannel());
        //change unit to minutes
        timeRules.get(0).setUnit("m");
        webhookService.handleUserAction(userActionDetails);
        verify(startCountingEventPublisher,times(1)).publishStartCountingEvent("cardId",(int) timeRules.get(0).getDuration()*60*1000,messageToSend,timeRules.get(0).getChannel());
    }

    public void testhandleStockRule() throws SlackApiException, IOException {
        //GIVEN
        UserActionDetailsDTO userActionExample = createUserActionDatailsDtoExample();
        StockRule rule = new StockRule();
        rule.setChannel("projet");
        rule.setMessage("test message");
        rule.setType("stock");
        rule.setStockRuleColumnName("Doing");
        rule.setNbCards(1);
        rule.setOwner("anyone");
        List<StockRule> rulesApplied = Arrays.asList(rule);
        final Board board = new Board();
        final User owner = new User();
        owner.setId(UUID.randomUUID().toString());
        board.setOwner(owner);
        //WHEN
        doReturn(board).when(boardRepository).getById(anyString());
        List<?> cards = Arrays.asList(new CardDTO(),new CardDTO());
        doReturn(null).when(messageService).sendSlackMessage(any(),any());
        doReturn(rulesApplied).when(ruleRepository).getActiveStockRulesByBoardAndTypeAndColumnName(any(Board.class),anyString(),anyString());
        doReturn(cards).when(projectManagementService).getCardsOfTrelloList(any(),anyString() );
        webhookService.handleStockRule(userActionExample);

        //THEN
        Assertions.assertDoesNotThrow(()->messageService.sendSlackMessage(rule.getMessage(),rule.getChannel()));
    }

}
