    
    
    
    
    
    /*Programme de Christopher Marie-Angélique 
    et de Léontin Walter*/
    
    
    /**
     * Menu
     *
     * 
     */
    
    import java.util.Scanner;
    import java.io.*;
    import java.util.*;
    import java.text.*;
    
    public class Menu
    {
        
            //texte du menu
            public static String [][] promo = null;
            public static int nbItemMenu = 2;
            public static String texteMenu = "\n/**********************************************/\n"
                    +"\t\t1 - un etudiant dans la promo\n"+"\t\t2 - affiche la promo\n\t\t0 - Quitter\n"+"/**********************************************/\n\n";
                    
                    
        /**  permet de retourner une valeur entiere saisie au clavier comprise entre pfBorneInf et pfBorneSup
         *@param pfBorneSup In la borne sup
         *@param pfBorneInf In la borne inf
         *@param pfMessage In message à afficher
             *@return valeur entiere comprise entre pfBorneInf et pfBorneSup
             **/
        public static int saisieIntC (int pfBorneInf,  int pfBorneSup, String pfMessage){
            int valeur;
            Scanner clavier = new Scanner(System.in) ;
            System.out.print(pfMessage); 
            
            valeur=clavier.nextInt();
            while (valeur<pfBorneInf || valeur>pfBorneSup){
                System.out.println(pfMessage);
                System.out.print("Erreur ! Donnez une valeur comprise " + pfBorneInf +" et "+pfBorneSup+ "?");
                valeur=clavier.nextInt();
            }
            return valeur;
        }
        
        /**  traite le choix 1
        *@param vous pouvez en ajouter 
            *@return un étudiant correspondant aux critères
            **/
        
        
        /** determine si une valeur est présente dans le tableau 
         *@param pfhistorique In historique des étudiants qui sont passés
         *@param pfNbEltHisto In nombre d'étudiants dans l'historique
         *@param pftirage In emplacement dans le tableau de l'etudiant tiré au sort
             *@return si l'étudiant a déjà été tiré au sort
             **/
        public static boolean estPresent(int pfhistorique[], int pfNbEltHisto, int pftirage) {
            //initialise la variable estPresent a Faux, donc l'index n'est pas present dans le tableau
            boolean estPresent = false ;  
            for ( int i = 0 ; i < pfNbEltHisto ; i ++) {
                //analyse si l'index du tirage est deja present dans le tableau
                if (pfhistorique[i] == pftirage){ 
                    //si l'index est present dans le tableau,retourne vrai
                    return true ;
                }
            }
            return estPresent ;
        }
        
        /**  traite le choix 2
         *@param pfhistorique In historique des étudiants qui sont passes
         *@param pfNbEltHisto In nombre d'etudiants dans l'historique 
             *@return un étudiant tiré au sort
             **/
        public static int traiterChoix2(int pfhistorique[], int pfNbEltHisto){
          
          //tirage au sort d'un entier compris dans la taille du tableau de la liste des etudiants  
          int tirage = (int) (Math.random() * promo.length);
          
        
            return tirage;
        }
        
         /**  affiche le menu et exécute les choix...
             *@return un étudiant correspondant aux critères
             **/
            public static void testMenu(){
             
               
               int choixUtilisateur ;
               String etu[];   
               //tableau qui stocke les indice des etudiant tirer
               int historique[]; 
               //indice de l'etudiant initialiser a 0
               int nbEltHisto = 0;
               //la taille du tableau historique prend la taille de la liste des etudiants
               historique = new int [promo.length] ;  
               for (int i=0 ; i<historique.length ; i++){
                   //le nombre d'etudiants stocker dans le tableau est initialement 0
                   historique[i] = 0 ;
                }
               
                 
                                
                                
               do
                {
                    System.out.println(texteMenu);
                    choixUtilisateur = saisieIntC ( 0, nbItemMenu, "Choisissez parmis les 3 propositions : ");
    
                    try {
                        switch (choixUtilisateur)
                        {
                            case 2 :
                                  
                                /* afficher promo */
                                 for (int i=0;i<ListeEtudiants.nbEtudiant(promo);i++){
                                      
                                     System.out.println("etu : "+(i+1) +"\t"+promo [i][ 0]+"\t" +promo [i][1]);
                                }
                      
                                break ;
                            case 1 : //traite le choix 1
                                 //tire au sort le premier etudiant
                                int tirage = traiterChoix2(historique, nbEltHisto); 
                                //assure que l'étudiant tiré au sort coresspond aux critères 
                                while (nbEltHisto == tirage || estPresent( historique, nbEltHisto, tirage) ) {
                                    tirage = traiterChoix2(historique, nbEltHisto); 
                                } 
                                //afiche l'eleve tiré au sort
                                System.out.println(promo [tirage][ 0]+" " +promo [tirage][1]);    
                                if (nbEltHisto < promo.length){
                                    //stockage de l'étudiant dans le tableau
                                  historique[ nbEltHisto ] = tirage ; 
                                  //le nbe d'étudiant stockée augmente de 1
                                  nbEltHisto = nbEltHisto + 1 ;  
                                   //affiche le nbe d'étudiant tirer
                                  System.out.println ("le nouveau nombre d'étudiant tirer est " + " " + nbEltHisto) ;                   
                                }else{
                                 System.out.println("Fin, Réinitialisation de l'historique !");
                                     for (int i=0 ; i<historique.length ; i++){
                                         //remise à 0 du l'historique
                                        historique[i] = 0 ;
                                     }  
                                 nbEltHisto = 0;
                                }
                            
                               
                                  
                                break ;
                            
                            case 0 :
                                System.out.println ("AU REVOIR ...   ...\n");
                                 
                                break ;
                            default :
                                System.out.println("\n\n\nBIZARRE ... \n\n\n");
                                break;
                        } 
                    }catch (Exception e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                }
                while (choixUtilisateur != 0);
         
        }
        
         
     
        public static void main(String arguments[]) {
            
            try {
                 
                    promo = ListeEtudiants.getListe("listenomssansaccent.csv", ","); //appel du sous programme précédé du nom de la classe où elle est définie
                   
                     System.out.println("Il y a : " + ListeEtudiants.nbEtudiant(promo) + " etudiants"); 
                    testMenu();
                   
            }
            catch (Exception e) {  
                System.out.println("Erreur : "+e.getMessage());
                     
              } 
         
      } // fin main
}