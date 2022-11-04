package orange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class RecupData {

	/*----------------------------------------------------------------------*/
	/*                               Constantes                             */
	/*----------------------------------------------------------------------*/
	public static String pathOrangeData  = "/donnees/";
	public static String nomFichierTrace = "/orange_data_2021.txt";
	/*----------------------------------------------------------------------*/

	private ArrayList<Evenement> listeEv;
	private HashMap<String, ArrayList<Evenement>> hashEv;
	private HashMap<Integer, ArrayList<Evenement>> hashHeure;

	public RecupData(String fichier) {
		this.listeEv   = new ArrayList<Evenement>();
		this.hashEv    = new HashMap<String,  ArrayList<Evenement>>();
		this.hashHeure = new HashMap<Integer, ArrayList<Evenement>>();
		
		lireDonnees(fichier);
		
		grouperEv();
		grouperHeure();
	}
	

	/*-------------------------------------------------------------------------------*/
	/*                                   Etape 3                                     */
	/*-------------------------------------------------------------------------------*/
	/**
	 * Permet de lire les donnees du .txt orange_data_2021
	 * @param fichier Permet de récupérer le nom du fichier que LireDonnees doit lire
	 */
	private void lireDonnees(String fichier) {
		String ligne = null;

		try { // ouverture de la ressource vue comme flux de données

			InputStream       ips  = this.getClass().getResourceAsStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader    br   = new BufferedReader(ipsr);
			
			// traitement
			while ((ligne = br.readLine()) != null) {
				traiteLigne(ligne);
			}
			
			// fermeture
			br.close();
		} catch (Exception exc) {
			System.out.println("Erreur fichier" + exc);
		}
	}

	/**
	 * Permet de créer un objet Evenement pour chaque lignes du fichier txt et
	 * l'ajoute dans l'ArrayList listeEv
	 * 
	 * @param ligne : String correspondant a la ligne en cours de traitement dans la methode LireDonnees()
	 */
	private void traiteLigne(String ligne) {
		int    num_alias;
		String time;
		String iso_country;
		String country;
		String type_ev;
		String categ_ev;
		int    x;
		int    y;
		String information;
		int    num_mat;
		
		String[] temp = ligne.split(";");
		
		num_alias   = Integer.parseInt(temp[0]);
		time        = temp[1];
		iso_country = temp[2];
		country     = temp[3];
		type_ev     = temp[4];
		categ_ev    = temp[5];
		x           = Integer.parseInt(temp[6]);
		y           = Integer.parseInt(temp[7]);
		information = temp[8];
		num_mat     = Integer.parseInt(temp[9]);
		
		this.listeEv.add(new Evenement(num_alias, time, iso_country, country, type_ev, categ_ev, x, y, information, num_mat));
	}
	/*-------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------*/
	/*                                   Etape 5                                     */
	/*-------------------------------------------------------------------------------*/
	/**
     * Permet de remplir la hashMap avec tout les territoires présent dans l'arrayList
     */
    private void grouperEv()
    {
        for (Evenement e : listeEv)
        {
            String type = e.getType_ev();
            if (this.hashEv.containsKey(type))
            {
                this.hashEv.get(type).add(e);
            }
            else
            {
                ArrayList<Evenement> alTemp = new ArrayList<Evenement>();
                alTemp.add(e);
                this.hashEv.put(type, alTemp);
            }
        }
    }
	
    /**
     * Permet de remplir la hashMap avec tout les territoires présent dans l'arrayList
     */
    private void grouperHeure()
    {
        for (Evenement e : listeEv)
        {
            int heure = e.getHeure();
            if (this.hashHeure.containsKey(heure))
            {
                this.hashHeure.get(heure).add(e);
            }
            else
            {
                ArrayList<Evenement> alTemp = new ArrayList<Evenement>();
                alTemp.add(e);
                this.hashHeure.put(heure, alTemp);
            }
        }
    }
    
    public String affichageGrouperHeure()
    {
        String sRet = "";
        for (int cpt : this.hashHeure.keySet())
                sRet += cpt + "," + this.hashHeure.get(cpt).size() + "\n";

        return sRet;
    }
    
    public String affichageGrouperEv()
    {
        String sRet = "";
        for (String cpt : this.hashEv.keySet())
                sRet += cpt + "," + this.hashEv.get(cpt).size() + "\n";

        return sRet;
    }
	
	public String toString() {
		return "RecupData [listeEv=" + listeEv.size() + ", hashEv=" + hashEv.size() + ", hashHeure=" + hashHeure.size() + "]";
	}
	
	public static void main(String[] args) {
		RecupData recupData = new RecupData(RecupData.pathOrangeData + RecupData.nomFichierTrace);
		
		System.out.println(recupData.toString());
		System.out.println(recupData.affichageGrouperEv());
		System.out.println(recupData.affichageGrouperHeure());
	}


}
