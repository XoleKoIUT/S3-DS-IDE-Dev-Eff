package orange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.text.html.parser.Element;

public class RecupData {
	public static String pathOrangeData  = "/donnees/";
	public static String nomFichierTrace = "/orange_data_2021.txt";

	private ArrayList<Evenement>                     listeEv;
	private HashMap  <String , ArrayList<Evenement>> hashEvent;
	private HashMap  <Integer, ArrayList<Evenement>> hashHeure;
	
	/* Etape 7 */
	private ArrayList<Heure> listeHeure;
	
	public RecupData(String fichier) {
		
		this.listeEv    = new ArrayList<Evenement>(); 
		this.hashEvent  = new HashMap  <String, ArrayList<Evenement>>();
		this.hashHeure  = new HashMap  <Integer, ArrayList<Evenement>>();
		this.listeHeure = new ArrayList<Heure>();
		
		lireDonnees(fichier);
		grouperEv();
		grouperHeure();
	}

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
		
		/* Creation d'un nouveau joueur */
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

	private void lireDonnees(String fichier) {
		String ligne = null;

		try { // ouverture de la ressource vue comme flux de données

			InputStream       ips  = this.getClass().getResourceAsStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader    br   = new BufferedReader(ipsr);
			
			ligne = br.readLine();
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
	/*--------------------------------------------------------------------*/
	/*                              Etape 4                               */
	/*--------------------------------------------------------------------*/
	
	public void grouperEv() {
		for (Evenement e : listeEv) {
			String type = e.getType_ev();
			
			if (this.hashEvent.containsKey(type)) {
				this.hashEvent.get(type).add(e);
			} else {
				ArrayList<Evenement> alTemp = new ArrayList<Evenement>();
				alTemp.add(e);
				this.hashEvent.put(type, alTemp);
			}
		}
	}
	
	public void grouperHeure() {
		for (Evenement e : listeEv) {
			int heure = e.getHeure();
			if (this.hashHeure.containsKey(heure)) {
				this.hashHeure.get(heure).add(e);
			} else {
				ArrayList<Evenement> alTemp = new ArrayList<Evenement>();
				alTemp.add(e);
				this.hashHeure.put(heure, alTemp);
			}
		}
	}
	
	
    public String affichageGrouperHeure()
    {
        String sRet = "";
        for (int nomJ : this.hashHeure.keySet())
                sRet += nomJ + "," + this.hashHeure.get(nomJ).size() + "\n";

        return sRet;
    }
    
    public String affichageGrouperEv()
    {
        String sRet = "";
        for (String nomJ : this.hashEvent.keySet())
                sRet += nomJ + "," + this.hashEvent.get(nomJ).size() + "\n";

        return sRet;
    }
    /*----------------------------------------------------------------------*/

    
    /*----------------------------------------------------*/
    /*                      Etape 5                       */
    /*----------------------------------------------------*/
    public int nbEv(int numHeure) {
    	return hashHeure.get(numHeure).size();
    }
    
    public int nbEvT(String typeEv) {
    	return hashEvent.get(typeEv).size();
    }
	/*----------------------------------------------------*/
	
    
    /*----------------------------------------------------*/
    /*                      Etape 7                       */
    /*----------------------------------------------------*/
    /**
     * Permet de trier les joueurs de celui qui a le moins de modifications à celui qui a le plus de modifications
     * @return ArrayList<Heure>
     */
    public ArrayList<Heure> creerListeHeure()
    {
        for (Integer heure : this.hashHeure.keySet())
            listeHeure.add(new Heure(heure, nbEv(heure)));

        Collections.sort(listeHeure);
        return listeHeure;
    }
  
    public int nbEvMax() {
    	return listeHeure.get(23).getHe();
    }
    
	public String toString() {
		return "RecupData [listeEv=" + listeEv.size() + ", hashEvent=" + hashEvent.size() + ", hashHeure=" + hashHeure.size() + "]";
		
	}

	public static void main(String[] args) {
		RecupData recupData = new RecupData(pathOrangeData + nomFichierTrace);
		
		
		/*System.out.println(recupData.toString());
		System.out.println(recupData.affichageGrouperHeure());
		System.out.println(recupData.affichageGrouperEv());*/
		System.out.println(recupData.creerListeHeure());
		
		
		/*--------------------------------------*/
		/*              Test Etape 5            */
		/*--------------------------------------*/
		/*System.out.println(recupData.nbEv(2));
		System.out.println(recupData.nbEv(4));
		System.out.println(recupData.nbEv(6));
		
		System.out.print("\n");
		
		System.out.println(recupData.nbEvT("Location update"));
		System.out.println(recupData.nbEvT("Message sortant"));
		System.out.println(recupData.nbEvT("Allumage du telephone"));*/
		/*--------------------------------------*/
	}
}
