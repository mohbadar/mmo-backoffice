package af.gov.anar.lib.json.date;

//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ChildCar {
	public String companyName;
	public String modelName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public ChildCar(String companyName, String modelName) {
		super();
		this.companyName = companyName;
		this.modelName = modelName;
	}

	public ChildCar() {
		this.companyName = " ";
		this.modelName = " ";
	}

}
