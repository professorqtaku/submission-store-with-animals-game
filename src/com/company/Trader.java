package com.company;

import java.util.ArrayList;

public class Trader extends Store{

    private ArrayList<Player> players;

    public Trader(Game game, ArrayList<Player> players){
        super(game);
        this.players = players;
    }

    public void visit(Player visitor){
        this.visitor = visitor;
        trade();
    }

    public void trade(){
        int action = Printer.askPlayerWithOptions(true,"What do you want to do?",
                "Buy dragons from other players",
                "Sell dragons to other players");
        switch(action){
            case 1 -> buyFromPlayer();
            case 2 -> sellToPlayer();
        }
    }

    private void buyFromPlayer(){
        Player seller = getPlayer();
        Dragon dragonToBuy = getDragon(seller);

        if(seller != null & dragonToBuy != null){
            System.out.println("Choose -1 for not buying");
            int offer = Printer.askPlayerNumber(true, "How much do you want to offer to " +
                    TextColour.BLUE + seller.getName() + TextColour.RESET + " for " +
                    dragonToBuy.name + " the " + dragonToBuy.getClass().getSimpleName() + "?", visitor.getBalance(),-1);
            returnToGame(offer < 0,!game.actionDone);
            game.actionDone = true;
            System.out.println(TextColour.GREEN + "Turn the computer to [" + seller.getName() + "]");

            switch (Printer.askPlayerWithOptions(true, "Do you accept the offer?", "Yes", "No")){
                case 1 -> {
                    seller.removeDragon(dragonToBuy,true, offer);
                    visitor.addDragon(dragonToBuy,true,offer);
                    System.out.println(TextColour.GREEN + "Trade successful" + TextColour.RESET);
                }
                case 2 -> {
                    System.out.println(TextColour.GREEN + "Trade unsuccessful" + TextColour.RESET);
                }
            }
            System.out.println(TextColour.GREEN + "Turn the computer to [" + visitor.getName() + "]");
            if(askBuyMore("dragons", "trade")){
                trade();
            }
        }

    }

    private Player getPlayer(){
        System.out.println("< Trade: PLAYER >");
        ArrayList<Player> potentialSellers = new ArrayList<>();
        for(var player: players){
            if(!player.losing() && player.getOwnedDragons().size() > 0){
                potentialSellers.add(player);
                System.out.println(potentialSellers.size() + ". " + player.getName());
            }
        }
        checkListAndWarn(potentialSellers.size());
        int playerIndex = Printer.askPlayerNumber(true,
                "Choose a player you want to buy dragons from.",potentialSellers.size(), 0) -1;
        returnToGame(playerIndex < 0 , !game.actionDone);
        if(playerIndex >= 0){
            return potentialSellers.get(playerIndex);
        }
        return null;
    }

    private Dragon getDragon(Player seller){
        if(seller != null){
            System.out.println("< Trade: DRAGON >");
            seller.printAllOwnedDragons();
            int dragonIndex = Printer.askPlayerNumber(true, "Choose the dragon you want to buy",
                    seller.getOwnedDragons().size(),0)-1;
            returnToGame(dragonIndex < 0 , !game.actionDone);
            if(dragonIndex >= 0){
                return seller.getOwnedDragons().get(dragonIndex);
            }
        }
        return null;
    }

    protected void checkListAndWarn(int listToCheckSize){
        if(listToCheckSize == 0){
            System.out.println(TextColour.RED + "There are no player you can trade with!" + TextColour.RESET);
            Printer.sleep(2000);
            returnToGame(!game.actionDone,true);
        }
    }

    private void sellToPlayer(){}




}
