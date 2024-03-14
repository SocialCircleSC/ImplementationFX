package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class PatronTableModel
{
	private final SimpleStringProperty patronId;
	private final SimpleStringProperty patronName;
	private final SimpleStringProperty patronAddress;
	private final SimpleStringProperty patronCity;
    private final SimpleStringProperty patronStateCode;
    private final SimpleStringProperty patronZip;
    private final SimpleStringProperty patronEmail;
    private final SimpleStringProperty patrondateOfBirth;
    private final SimpleStringProperty patronStatus;

	//----------------------------------------------------------------------------
	public PatronTableModel(Vector<String> patronData)
	{
		patronId =  new SimpleStringProperty(patronData.elementAt(0));
		patronName =  new SimpleStringProperty(patronData.elementAt(1));
        patronAddress =  new SimpleStringProperty(patronData.elementAt(2));
        patronCity =  new SimpleStringProperty(patronData.elementAt(3));
        patronStateCode =  new SimpleStringProperty(patronData.elementAt(4));
        patronZip =  new SimpleStringProperty(patronData.elementAt(5));
        patronEmail =  new SimpleStringProperty(patronData.elementAt(6));
        patrondateOfBirth =  new SimpleStringProperty(patronData.elementAt(7));
        patronStatus =  new SimpleStringProperty(patronData.elementAt(8));
	}

	//----------------------------------------------------------------------------
	public String getPatronId() {
        return patronId.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronId(String number) {
        patronId.set(number);
    }

    //----------------------------------------------------------------------------
	public String getPatronName() {
        return patronName.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronName(String name) {
        patronName.set(name);
    }

    //----------------------------------------------------------------------------
	public String getPatronAddress() {
        return patronAddress.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronAddress(String address) {
        patronAddress.set(address);
    }

     //----------------------------------------------------------------------------
	public String getPatronCity() {
        return patronCity.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronCity(String city) {
        patronCity.set(city);
    }

     //----------------------------------------------------------------------------
	public String getPatronStateCOde() {
        return patronStateCode.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronStateCode(String statecode) {
        patronStateCode.set(statecode);
    }

     //----------------------------------------------------------------------------
	public String getPatronZip() {
        return patronZip.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronZip(String zip) {
        patronZip.set(zip);
    }

     //----------------------------------------------------------------------------
	public String getPatronEmail() {
        return patronEmail.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronEmail(String email) {
        patronEmail.set(email);
    }

     //----------------------------------------------------------------------------
	public String getPatronDateOfBirths() {
        return patrondateOfBirth.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronDateOfBirth(String dob) {
        patronAddress.set(dob);
    }

     //----------------------------------------------------------------------------
	public String getPatronStatus() {
        return patronStatus.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronStatus(String status) {
        patronStatus.set(status);
    }
}
