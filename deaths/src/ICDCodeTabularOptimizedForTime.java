import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ICDCodeTabularOptimizedForTime implements ICDCodeTabular{

    private Map<String, String> codeDescriptionMap = new HashMap<>();

    public ICDCodeTabularOptimizedForTime(Path path){
        try(
                Stream<String> lines = Files.lines(path);
        ){
            lines.skip(88)
                    .map(String::trim)
                    .filter(s -> s.matches("[A-Z][0-9]{2}.*"))
                    .map(s -> s.split(" ", 2))
                    .forEach(strings ->
                            codeDescriptionMap.put(strings[0], strings[1])
                    );
        }
        catch(IOException e){
            throw new RuntimeException();
        }
    }

    @Override
    public String getDescription(String code) {
        return codeDescriptionMap.getOrDefault(code, "?");
    }
}
