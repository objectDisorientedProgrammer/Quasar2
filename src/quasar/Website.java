/*
 * Created: May 28, 2013
 */

package quasar;

public class Website extends Data
{
	private String url;

	public Website(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}