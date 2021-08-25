package sfdc.security;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class Profiles {

    /**
     * @return Stream of profiles from given directory
     */
    public static Stream<Profile> fromDirectory(String dirPath) throws IOException {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.{profile,profile-meta.xml}");
        return Files.walk(Paths.get(dirPath))
                .filter(matcher::matches)
                .map((Path p) -> new Profile(p.toAbsolutePath().toString()));

    }
}
