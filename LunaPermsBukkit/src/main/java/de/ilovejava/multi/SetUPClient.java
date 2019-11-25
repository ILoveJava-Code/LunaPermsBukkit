package de.ilovejava.multi;

import de.ilovejava.config.Config_Value;

public class SetUPClient extends Config_Value{
	public SetUPClient() {
		this.StartigConnection();
		MultiLogin ml = new MultiLogin(this.MulitHost(), this.MulitUser(), this.MutilPassword(), this.MulitPort());
		ml.run();
	}
}
