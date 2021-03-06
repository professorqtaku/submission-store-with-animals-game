package com.company;

import com.company.DragonSubClasses.*;

import java.util.ArrayList;

public class Hospital extends Store{


    public Hospital(Game game){
        super(game);
    }

    public void visit(Player visitor){
        this.visitor = visitor;
        healDragon();
    }

    private void healDragon(){
        System.out.println("< Hospital: DRAGONS >");
        ArrayList<Dragon> sickDragons = getSickDragons();
        checkListAndWarn(sickDragons.size());
        if(sickDragons.size()>0) {
            System.out.println("OBS! There are only 50% chance for survival!");
            Printer.printDragonList(sickDragons, "hospital", "Hospital fee");
            healDragonAction(sickDragons);
        }
    }

    private ArrayList<Dragon> getSickDragons(){
        ArrayList<Dragon> sickDragons = new ArrayList<>();
        if(visitor != null){
            for (var dragon : visitor.getOwnedDragons()) {
                if(dragon.sick){
                    sickDragons.add(dragon);
                }
            }
        }
        return sickDragons;
    }

    private void healDragonAction(ArrayList<Dragon> sickDragons){
        if(sickDragons.size() < 1){
            return;
        }
        System.out.println("ENTER 0 for NOT healing");
        int dragonIndex = (Printer.askPlayerNumber(true,
                "Which dragon do you want to heal?", sickDragons.size(),0)-1);
        returnToGame(dragonIndex == -1, !game.actionDone);
        if(dragonIndex >= 0) {
            Dragon dragonToHeal = sickDragons.get(dragonIndex);
            visitor.pay(dragonToHeal.getPriceNow()/2);
            if((int)(Math.random()*2) == 1){
                dragonToHeal.sick = false;
                dragonToHeal.health = 100;
                System.out.println(dragonToHeal.name + " is healed!");
            }
            else{
                dragonToHeal.sick = true;
                System.out.println(dragonToHeal.name + " couldn't be healed and is dead now...");
                visitor.removeDragon(dragonToHeal,false, 0);
                //sickDragons.remove(dragonToHeal);
            }
            game.actionDone = true;
            if (askBuyMore("Dragons", "heal")) {
                healDragon();
            }
        }
    }
}
