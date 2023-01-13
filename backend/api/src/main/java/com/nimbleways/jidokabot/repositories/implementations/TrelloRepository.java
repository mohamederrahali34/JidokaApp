package com.nimbleways.jidokabot.repositories.implementations;

import com.nimbleways.jidokabot.dto.trellodto.TrelloBoard;
import com.nimbleways.jidokabot.dto.trellodto.TrelloUser;
import com.nimbleways.jidokabot.repositories.IProjectManagementRepository;
import com.nimbleways.jidokabot.utils.RestTemplateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@AllArgsConstructor
public class TrelloRepository implements IProjectManagementRepository {

    private final RestTemplateUtils restTemplateUtils;
    public static final String APPLICATION_KEY = "e193dd7ec1a31738d605f78edd0e1180";
    public static final String TOKEN = "cfb62d29ab83337b5b144676adaf2a3ec07293ca83339e0506ca98dcb8e81f5a";
    private static final String TRELLO_API_BASE_URL = "https://api.trello.com/1/";



    @Override
    public TrelloBoard getTrelloBoardByIdAndToken(final String id, final String token) {
        final String permessions = "?key="+ APPLICATION_KEY +"&token="+ token;
        final String getBoardURl = TRELLO_API_BASE_URL +"/boards/"+id+ permessions;
        return restTemplateUtils.get(getBoardURl,TrelloBoard.class);
    }

    @Override
    public TrelloUser getConnectedUserByToken(final String token) {
        final String permessions = "?key="+ APPLICATION_KEY +"&token="+ token;
        final String getConnectedUserUrl = TRELLO_API_BASE_URL +"/members/me"+ permessions;
        return restTemplateUtils.get(getConnectedUserUrl,TrelloUser.class);
    }

    @Override
    public List<?> getBoardsOfUserByToken(final String id,final String token) {
        final String permessions = "?key="+ APPLICATION_KEY +"&token="+ token;
        final String getBoardsOfUserUrl = TRELLO_API_BASE_URL +"/members/"+id+"/boards"+ permessions;
        final List<?> trelloBoardList = new ArrayList<>();
        return restTemplateUtils.get(getBoardsOfUserUrl, trelloBoardList.getClass());
    }

    @Override
    public List<?> getColumnsOfBoardByToken(final String id,final  String token) {
        final String permessions = "?key="+ APPLICATION_KEY +"&token="+ token;
        final String getColumnsOfBoardUrl = TRELLO_API_BASE_URL+"/boards/"+id+"/lists"+permessions;
        final List<?> trelloBoardColumns = new ArrayList<>();
        return restTemplateUtils.get(getColumnsOfBoardUrl, trelloBoardColumns.getClass());
    }

    @Override
    public List<?> getCardsOfTrelloListByToken(final String id,final  String token) {
        final String permessions = "?key="+ APPLICATION_KEY +"&token="+ token;
        final String getCardsOfTrelloListdUrl = TRELLO_API_BASE_URL+"/lists/"+id+"/cards"+permessions;
        final List<?> trelloCardsList = new ArrayList<>();
        return restTemplateUtils.get(getCardsOfTrelloListdUrl, trelloCardsList.getClass());
    }
}
