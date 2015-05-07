package optimitzacio;

import java.sql.SQLException;

public class Optimitzacio {
    public static void main(String[] args) throws SQLException {
        
        JavaConnection.getConnection();
        
        //Aquest codi correspon a la creació automatitzada dels nous camps,
        //NO de la taula que els conté (operació d'update). 10000 registres.
        
        /*
        ArrayList<Temps> arlTemps = new ArrayList();
        
        for(int i=0; i<10000; i++){
            arlTemps.add(new Temps(i,i));
        }
        JavaConnection.insertTemps(arlTemps);
        */
        
        //S'executa per primer cop una consulta de seleció, aixó es fa perque
        //internament, se suposa, que té dades de cache que emmagatzema després
        //de la primera consulta, resultant que les següents consultes sempre 
        //siguin més rapides.
        JavaConnection.viewTempsNum1(1);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        
        //L'array d'entrada de proves.
        int[][] seleccio ={{7000,2010},{2000,4010},{5000,5010},{4000,7010}};
        
        //Inicialització de l'objecte cron, només cal fer-ho un cop ja que 
        //exsisteix el metode Restart que permet reinicalitzar el valor d'aquest.
        Cronometre cron = new Cronometre();
        
        //Execució del metode Start que inicia el cronometre.
        cron.Start();
        
        //Execucció de les consultes del camp indexat corresponent a Num1.
        System.out.println("Indexat: ");
         for(int i=0;i<seleccio.length*10;i++){
            JavaConnection.viewTempsNum1(seleccio[i%4][0]);
        }
         
        //Parem el cronometre d'exprés de fer un seguit de consultes, les cúals
        //s'han repetit 40 vegades, 10 per nombre. (Per poder veure més clarament,
        //el temps d'execució).
        cron.Stop();
        //Un println dels milisegons que han passat.
        System.out.println("\nTemps: "+cron.EllapsedMilliseconds());
        //Restart(), adivina.
        cron.Restart();
        
        //Aquesta secció fa el mateix que l'anterior secció però amb els camps no
        //indexats.
        System.out.println("No indexat: ");
        cron.Start();
        for(int i=0;i<seleccio.length*10;i++){
            JavaConnection.viewTempsNum2(seleccio[i%4][1]);
            
        }
        cron.Stop();
        System.out.println("\nTemps: "+cron.EllapsedMilliseconds());
        cron.Restart();
    
       
        JavaConnection.closeConnection();   
    }   
}