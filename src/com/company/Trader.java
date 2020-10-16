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
        Player seller = getPlayer(true,0); // get the player
        Dragon dragonToBuy = getDragon(seller, true); // get the dragon

        if(seller != null & dragonToBuy != null){
            System.out.println("ENTER -1 for not trading");
            int offer = Printer.askPlayerNumber(true, "How much do you want to offer to " +
                    TextColour.BLUE + seller.getName() + TextColour.RESET + " for " +
                    dragonToBuy.name + " the " + dragonToBuy.getClass().getSimpleName() + "?",
                    visitor.getBalance(),-1);
            returnToGame(offer < 0,!game.actionDone);
            game.actionDone = true;
            System.out.println(TextColour.GREEN + "Turn the computer to [" + seller.getName() + "]" + TextColour.RESET);

            switch (Printer.askPlayerWithOptions(true,
                    "Do you accept the offer (" + offer + ") for " + dragonToBuy.name+"?",
                    "Yes", "No")){
                case 1 -> {
                    seller.removeDragon(dragonToBuy,true, offer);
                    visitor.addDragon(dragonToBuy,true,offer);
                    System.out.println(TextColour.GREEN + "Trade successful" + TextColour.RESET);
                }
                case 2 -> {
                    System.out.println(TextColour.BLUE + "Trade unsuccessful" + TextColour.RESET);
                }
            }
            System.out.println(TextColour.GREEN + "Turn the computer to [" + visitor.getName() + "]" + TextColour.RESET);
            if(askBuyMore("dragons", "trade")){
                trade();
            }
        }
    }

    private void sellToPlayer(){
        Dragon dragonToSell = getDragon(visitor, false);
        if(dragonToSell != null) {
            System.out.println("ENTER -1 for not trading");
            int offer = Printer.askPlayerNumber(true, "How much do you want to offer for " +
                            dragonToSell.name + " the " + dragonToSell.getClass().getSimpleName() +
                            "(0-" + dragonToSell.price+ ")?",
                    dragonToSell.price,-1);
            returnToGame(offer < 0,!game.actionDone);
            if(offer >= 0) {
                Player buyer = getPlayer(false, offer);
                if (buyer != null) {
                    game.actionDone = true;
                    System.out.println(TextColour.GREEN + "Turn the computer to [" + buyer.getName() + "]" + TextColour.RESET);
                    switch (Printer.askPlayerWithOptions(true,
                            "Do you accept the offer (" + offer + ") for " + dragonToSell.name + "?",
                            "Yes", "No")) {
                        case 1 -> {
                            visitor.removeDragon(dragonToSell, true, offer);
                            buyer.addDragon(dragonToSell, true, offer);
                            System.out.println(TextColour.GREEN + "Trade successful" + TextColour.RESET);
                        }
                        case 2 -> {
                            System.out.println(TextColour.BLUE + "Trade unsuccessful" + TextColour.RESET);
                        }
                    }
                    System.out.println(TextColour.GREEN + "Turn the computer to [" + visitor.getName() + "]" + TextColour.RESET);
                    if (askBuyMore("dragons", "trade")) {
                        trade();
                    }
                }
            }
        }

    }

    private Player getPlayer(boolean visitorBuy, int offer){
        System.out.println("< Trade: PLAYER >");
        ArrayList<Player> potentialTraders = new ArrayList<>();
        for(var player: players){
            if(visitorBuy) {
                if (!player.losing() && player.getOwnedDragons().size() > 0 && player != visitor) {
                    potentialTraders.add(player);
                    System.out.println(potentialTraders.size() + ". " + player.getName());
                }
            }
            else{
                if (!player.losing() && player.getBalance() >= offer && player != visitor) {
                    potentialTraders.add(player);
                    System.out.println(potentialTraders.size() + ". " + player.getName());
                }
            }
        }
        checkListAndWarn(potentialTraders.size());
        if(potentialTraders.size()>0){
            System.out.println("ENTER 0 for not trading");
            int playerIndex = Printer.askPlayerNumber(true,
                    "Choose a player you want to " +
                            (visitorBuy ? "buy dragons from." : "sell dragons to."),
                    potentialTraders.size(), 0) - 1;
            returnToGame(playerIndex < 0, !game.actionDone);
            if (playerIndex >= 0) {
                return potentialTraders.get(playerIndex);
            }
        }
        return null;
    }

    private Dragon getDragon(Player seller, boolean visitorBuy){
        if(seller != null){
            System.out.println("< Trade: DRAGON >");
            seller.printAllOwnedDragons();
            int dragonIndex = Printer.askPlayerNumber(true, "Choose the dragon you want to " +
                            (visitorBuy ? "buy" : "sell") + ".",
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




}
