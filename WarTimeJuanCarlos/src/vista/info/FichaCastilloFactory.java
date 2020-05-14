package vista.info;

import javax.swing.JPanel;

import vista.FichaCastillo;

public class FichaCastilloFactory implements FichaInfo{
	private FichaCastilloInfo fichaCastilloInfo;
	
	
	public FichaCastilloFactory(FichaCastilloInfo fichaCastilloInfo) {
		super();
		this.fichaCastilloInfo = fichaCastilloInfo;
	}


	@Override
	public JPanel getPanel() {
		return new FichaCastillo(fichaCastilloInfo);
	}

}
