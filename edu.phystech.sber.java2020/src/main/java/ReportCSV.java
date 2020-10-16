import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ReportCSV implements Report{
    List<List<String>> objectList;
    String delimiter = ";";

    public ReportCSV(List<List<String>> objectList) {
        this.objectList = objectList;
    }

    public byte[] asBytes() {
        return new byte[0];
    }

    @Override
    public void writeTo(OutputStream os) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (List<String> row : objectList) {
            for (String column : row) {
                sb.append(column);
                sb.append(delimiter);
            }
            sb.append("\n");
        }
        os.write(sb.toString().getBytes());
    }
}
