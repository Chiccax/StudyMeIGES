package test.integration.manager;


import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.bean.RecensioneBean;
import model.dao.RecensioneDao;
import model.manager.LezioneManager;
class Test_RecensioneManager {

	@Test
	void testAggiungiRecensione() {
		manager.aggiungiRecensione("Damiana", "pac043", "Pacchetto ottimo!", "Consigliatissimo!");
		
		ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
		recensioni = managerLezioni.getRecensioni("pac043");
		RecensioneBean recensioneAttuale = null;
		
		for(RecensioneBean r : recensioni) {
			if((r.getCliente()).equals("Damiana"))
				recensioneAttuale = r;
		}
		
		assertNotNull(recensioneAttuale);
		
	}

	private RecensioneDao manager = new RecensioneDao();
	private LezioneManager managerLezioni = new LezioneManager();
}
