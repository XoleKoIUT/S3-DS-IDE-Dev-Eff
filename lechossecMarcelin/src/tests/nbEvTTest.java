package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import orange.RecupData;

public class nbEvTTest {

	@Test
	public void test() {
		RecupData recupData = new RecupData(RecupData.pathOrangeData + RecupData.nomFichierTrace);
		assertEquals(170, recupData.nbEvT("Location update"));
		assertEquals(81, recupData.nbEvT("Message sortant"));
		assertEquals(120, recupData.nbEvT("Allumage du telephone"));
	}

}