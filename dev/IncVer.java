import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class IncVer extends Task {

	private String jsUrl;
	private File file;

	public void setName(String name) {
		jsUrl = name + "/" + name + ".nocache.js?v=";
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void execute() throws BuildException {
		String curVer = Long.toHexString(System.currentTimeMillis());
		String lastVer = "unknown_version";

		File fileTmp = new File(file.getParentFile(), file.getName() + ".tmp");
		try (BufferedReader reader = new BufferedReader(new FileReader(file)); //
				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileTmp), "UTF-8"));) {

			String line;
			do {
				line = reader.readLine();
				if (line != null) {
					int i = line.indexOf(jsUrl);
					int j = i;
					if (i >= 0) {
						i += jsUrl.length();
						j = line.indexOf("\">", i);
					}
					if (j >= 0) {
						lastVer = line.substring(i, j);

						writer.write(line.substring(0, i));
						writer.write(curVer);
						writer.write(line.substring(j));
					} else {
						writer.write(line);
					}
					writer.write(System.getProperty("line.separator"));
				}
			} while (line != null);
		} catch (Exception e) {
			throw new RuntimeException("[ERROR] cannot increment javascript url version in " + file.getAbsolutePath(),
					e);
		}

		System.out.println("Increasing javascript url version from " + lastVer + " to " + curVer + ".");

		File fileBak = new File(file.getParentFile(), file.getName() + ".bak");
		fileBak.delete();
		if (!file.renameTo(fileBak)) {
			throw new RuntimeException(
					"[ERROR] " + file.getAbsolutePath() + " cannot be renamed to " + fileBak.getAbsolutePath());
		}
		if (!fileTmp.renameTo(file)) {
			throw new RuntimeException(
					"[ERROR] " + fileTmp.getAbsolutePath() + " cannot be renamed to " + file.getAbsolutePath());
		}

		System.out.println("Javascript url version increased in " + file.getAbsolutePath());
	}
}
