package be.renaud11232.bluemapspawn.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class AssetExtractor implements AutoCloseable {
    private final Path destination;
    private final FileSystem jar;
    private final Path sourceDirectory;

    public AssetExtractor(Class<?> clazz, String source, Path destination) throws IOException {
        this.destination = destination;
        try {
            this.jar = FileSystems.newFileSystem(Path.of(clazz.getProtectionDomain().getCodeSource().getLocation().toURI()));
            this.sourceDirectory = jar.getPath(source);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void extract() throws IOException {
        List<Path> sourceFiles = listSourceFiles();
        copyFiles(sourceFiles);
    }

    private List<Path> listFiles(Path absolutePath) throws IOException {
        try (Stream<Path> absoluteFiles = Files.walk(absolutePath)) {
            return absoluteFiles
                    .map(absolutePath::relativize)
                    .toList();
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }
    }

    private void copyFiles(List<Path> sourceFiles) throws IOException {
        CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
        if (!Files.exists(destination)) {
            Files.createDirectories(destination);
        }
        for (Path sourceFile : sourceFiles) {
            try {
                Files.copy(sourceDirectory.resolve(sourceFile), destination.resolve(sourceFile.toString()), options);
            } catch (DirectoryNotEmptyException ignored) {
                //Copying a directory that already exists, oh no... Anyway
            }
        }
    }

    private List<Path> listSourceFiles() throws IOException {
        return listFiles(sourceDirectory);
    }

    public List<Path> listDestinationFiles() throws IOException {
        return listFiles(destination);
    }

    @Override
    public void close() throws IOException {
        this.jar.close();
    }
}
