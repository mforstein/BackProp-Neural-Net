 
import java.util.ArrayList;

/**
 * Write a description of class TestCase here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestCase {
	// instance variables - replace the example below with your own
	private Integer keys;
	private ArrayList<Double> Inputs;
	private ArrayList<Double> Outputs;

	public TestCase() {
		setKeys(0);
		Inputs = new ArrayList<Double>();
		Outputs = new ArrayList<Double>();

	}

	public Integer getIsize() {

		return Inputs.size();

	}

	public Integer getOsize() {

		return Outputs.size();

	}

	public void AddTestCaseKey(Integer key) {
		setKeys(key);

	}

	public void AddTestCaseInput(Double Input) {

		Inputs.add(Input);

	}

	public void AddTestCaseOutput(Double Output) {

		Outputs.add(Output);

	}

	public void RemoveTestCase(Integer key, Double Input, Double Output) {
		setKeys(0);
		Inputs.remove(Input);
		Outputs.remove(Output);

	}

	public Double getInput(Integer key) {

		return Inputs.get(key);

	}

	public Double getOutput(Integer key) {

		return Outputs.get(key);

	}

	public String getInputs() {

		return Inputs.toString();
	}

	public String getOutputs() {
		return Outputs.toString();
	}

	public ArrayList<Double> getInputs2() {

		return Inputs;
	}

	public ArrayList<Double> getOutputs2() {
		return Outputs;
	}

	public void setInputs(ArrayList<Double> Input) {

		Inputs = Input;
	}

	public void setOutputs(ArrayList<Double> Output) {

		Outputs = Output;

	}

	public void clear() {
		Inputs.clear();
		Outputs.clear();
		setKeys(0);
	}

	/**
	 * @return the keys
	 */
	public Integer getKeys() {
		return keys;
	}

	/**
	 * @param keys
	 *            the keys to set
	 */
	public void setKeys(Integer keys) {
		this.keys = keys;
	}
}
