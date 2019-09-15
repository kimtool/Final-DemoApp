package com.novi.minigames;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);                
            System.out.print("Vul de naam van speler 1 in: ");      //Get name of player 1   
            String player1Name=input.nextLine(); 
            Player player1=new Player(player1Name, 'X');            //      X's and O's for player pawns
            System.out.print("Vul de naam van speler 2 in: ");      //Get name of player 2   
            String player2Name=input.nextLine(); 
            Player player2=new Player(player2Name, 'O');            
            String currentPlayer=player1.Name();
            
            boolean exit=false;              
            do {    
                System.out.println("\n" + "Welk spel wil je spelen?" + "\n" + "1:TicTacToe" + "\n" + "2:Four in a row" + "\n" + "3:Exit");   //Print Menu             
                Scanner inputGame = new Scanner(System.in);     //Get chosen game  
                int game=inputGame.nextInt();             
                System.out.println();   //witregel       
                switch(game){ 
                    
                    case 1:           //Start chosen game               
                            Field[] field = new Field [9];        //Use:  Dots and/or numbers for fields
                            char chTTT = '1';
                            for(int x = 0; x< 3; x++){  
                                for(int y = 0; y < 3; y++){ 
                                    field[y+x*3] = new Field(chTTT++);
                                }                        
                            } 
                        for (int end=0; end<9; end++) {                      
                            for(int x = 0; x< 3; x++){
                                for(int y = 0; y < 3; y++){ 
                                    System.out.print(field[y+x*3].value +" ");      //Print board 
                                }
                                System.out.println();    
                            }
                            System.out.println();  //witregel            
                            System.out.println("Huidige speler is: " +currentPlayer);       //Print current player                          
                            System.out.print("Kies een vakje ");        //Get chosen field  
                            int move = input.nextInt();
                            System.out.println();   //witregel            
                            if(move > 0 && move <= 9 && field[move-1].value != player1.Token() && field[move-1].value != player2.Token()) {      //if not in debug more, we check if the chosen field exists            
                                field[move-1].value = currentPlayer == player1.Name() ? player1.Token() : player2.Token();   //we get the field index (field - 1) and set it to X or O depending on the currentplayer                                    
                            } 
                            else {
                                    continue;                    
                            }                      
                            if (field[0].value == field[1].value && field[0].value == field[2].value||      //If game is won
                                field[3].value == field[4].value && field[3].value == field[5].value||
                                field[6].value == field[7].value && field[6].value == field[8].value||
                                field[0].value == field[3].value && field[0].value == field[6].value||
                                field[1].value == field[4].value && field[1].value == field[7].value||
                                field[2].value == field[5].value && field[2].value == field[8].value||
                                field[0].value == field[4].value && field[0].value == field[8].value||
                                field[2].value == field[4].value && field[2].value == field[6].value){
                            end=9;                    
                                System.out.println(currentPlayer +" has won!");
                                if (currentPlayer == player1.Name())        //Winner gets a point
                                    player1.addScore();                                
                                else player2.addScore();
                            }                                    
                            else{
                                currentPlayer = currentPlayer == player1.Name() ? player2.Name() : player1.Name();      //If game is not won//Switch players
                            }                                 
                        }       //Start next turn            
                        for(int x = 0; x< 3; x++){      //Print board
                            for(int y = 0; y < 3; y++){ 
                                System.out.print(field[y+x*3].value+" ");
                            }
                            System.out.println();
                        }
                        System.out.println();              
                        System.out.println("Eindstand:"+ "\n" + player1.Name() +": " +player1.Score()+" punten" + "\n" +player2.Name()+": "+player2.Score()+" punten");     //Print scores             
                        continue;       //Go back to menu 
                    case 2:                       
                        boolean win=false;
                        Field[][] field4IR = new Field [6][7];
                        for(int x = 0; x < 6; x++){  
                            for(int y = 0; y < 7; y++){ 
                                field4IR [x][y]= new Field('.');                             
                            }                        
                        }
                        System.out.println();   //witregel
                        for (int end=0; end<42; end++) {
                            for(int x = 0; x< 6; x++){  
                                for(int y = 0; y < 7; y++){ 
                                    System.out.print(field4IR[x][y].value+" ");                             
                                }
                                System.out.println();
                            }
                            System.out.println("1 2 3 4 5 6 7");
                            System.out.println("Huidige speler is: " +currentPlayer);              
                            System.out.print("Kies een rij ");
                            int move = input.nextInt();
                            System.out.println();   //witregel
                            move--;
                            int xx=5;                                                    
                            if(move >= 0 && move < 7) {                                
                                while (xx>0 && field4IR[xx][move].value == player1.Token() || field4IR[xx][move].value == player2.Token()){                                      
                                    xx--;                                                                
                                }     
                                field4IR[xx][move].value = currentPlayer == player1.Name() ? player1.Token() : player2.Token();
                            }                                
                            else {
                                continue;                   
                            }                        
                            for (int y=0; y<7; y++){ //verticale win
                                for (int x=0; x<3; x++){
                                    if (field4IR[x][y].value==player1.Token() && field4IR[x+1][y].value==player1.Token() && field4IR[x+2][y].value==player1.Token() && field4IR[x+3][y].value==player1.Token() ||
                                        field4IR[x][y].value==player2.Token() && field4IR[x+1][y].value==player2.Token() && field4IR[x+2][y].value==player2.Token() && field4IR[x+3][y].value==player2.Token()){
                                        win=true; 
                                    }
                                }                                        
                            }
                            for (int x=0; x<6; x++){ //horizontale win
                                for (int y=0; y<4; y++){
                                    if (field4IR[x][y].value==player1.Token() && field4IR[x][y+1].value==player1.Token() && field4IR[x][y+2].value==player1.Token() && field4IR[x][y+3].value==player1.Token() ||
                                        field4IR[x][y].value==player2.Token() && field4IR[x][y+1].value==player2.Token() && field4IR[x][y+2].value==player2.Token() && field4IR[x][y+3].value==player2.Token()){
                                        win=true;
                                    }
                                }                                        
                            }
                            for (int x=0; x<3; x++){ //schuine win /
                                for (int y=0; y<4; y++){
                                    if (field4IR[x+3][y].value==player1.Token() && field4IR[x+2][y+1].value==player1.Token() && field4IR[x+1][y+2].value==player1.Token() && field4IR[x][y+3].value==player1.Token() ||
                                        field4IR[x+3][y].value==player2.Token() && field4IR[x+2][y+1].value==player2.Token() && field4IR[x+1][y+2].value==player2.Token() && field4IR[x][y+3].value==player2.Token()){
                                        win=true;
                                    }
                                }                                        
                            }
                            for (int x=0; x<3; x++){ //schuine win \
                                for (int y=0; y<4; y++){
                                    if (field4IR[x][y].value==player1.Token() && field4IR[x+1][y+1].value==player1.Token() && field4IR[x+2][y+2].value==player1.Token() && field4IR[x+3][y+3].value==player1.Token() ||
                                        field4IR[x][y].value==player2.Token() && field4IR[x+1][y+1].value==player2.Token() && field4IR[x+2][y+2].value==player2.Token() && field4IR[x+3][y+3].value==player2.Token()){
                                        win=true;
                                    }
                                }                                        
                            }                   
                            if (win==true){
                                end=42;                    
                                System.out.println(currentPlayer +" has won!");
                                if (currentPlayer == player1.Name())
                                    player1.addScore();                                
                                else player2.addScore();
                            }                         
                            else{
                                currentPlayer = currentPlayer == player1.Name() ? player2.Name() : player1.Name();
                            }
                        }
                            for(int x = 0; x< 6; x++){  
                                for(int y = 0; y < 7; y++){ 
                                    System.out.print(field4IR[x][y].value+" ");                             
                                }
                                System.out.println();
                            }
                            System.out.println();   //witregel
                        System.out.println("Eindstand:"+ "\n" + player1.Name() +": " +player1.Score()+" punten" + "\n" +player2.Name()+": "+player2.Score()+" punten");
                        continue;
                    case 3:
                        System.out.println("Bye!");
                        exit=true;
                    default:
                        continue;
                }
            }while(exit==false);
    }
}