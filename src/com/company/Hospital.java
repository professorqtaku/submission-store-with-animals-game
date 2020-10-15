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
        ArrayList<Dragon> sickDragons = getSickDragons();
        checkListAndWarn(sickDragons.size());
        healDragonAction(sickDragons);
    }

    private ArrayList<Dragon> getSickDragons(){
        System.out.println("< Hospital: DRAGONS >");
        ArrayList<Dragon> sickDragons = new ArrayList<>();
        if(visitor != null){
            for (var dragon : visitor.getOwnedDragons()) {
                if(dragon.sick){
                    sickDragons.add(dragon);
                    System.out.println(sickDragons.size() + ". " + dragon.name);
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
            if((int)(Math.random()*2) == 1){
                dragonToHeal.sick = false;
                System.out.println(dragonToHeal.name + " is healed!");
            }
            else{
                dragonToHeal.sick = true;
                System.out.println(dragonToHeal.name + " couldn't be healed and is dead now...");
                if(!dragonToHeal.living()){
                    System.out.println("living" + dragonToHeal.living());
                    visitor.removeDragon(dragonToHeal,false, 0);
                }
            }
            game.actionDone = true;
            if (askBuyMore("Dragons", "heal")) {
                healDragon();
            }
        }
    }
}
