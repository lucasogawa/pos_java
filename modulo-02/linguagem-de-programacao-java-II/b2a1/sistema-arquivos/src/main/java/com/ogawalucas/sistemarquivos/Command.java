package com.ogawalucas.sistemarquivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.Arrays;
import java.util.Optional;

public enum Command {

    LIST() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("LIST") || commands[0].startsWith("list");
        }

        @Override
        Path execute(Path path) {
            System.out.println("Contents of " + path.toString());

            Optional.ofNullable(path.toFile().listFiles())
                .filter(files -> files.length != 0)
                .ifPresentOrElse(
                    files -> Arrays.stream(files)
                        .map(File::getName)
                        .forEach(System.out::println),
                    () -> System.out.println("It is a empty directory.")
                );

            return path;
        }
    },
    SHOW() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("SHOW") || commands[0].startsWith("show");
        }

        @Override
        Path execute(Path path) {
            if (parameters.length == 1) {
                throw new UnsupportedOperationException("It is necessary to inform a file name.");
            }

            final var newPath = Paths.get(path.toAbsolutePath() + "/" + parameters[1]);

            if (Files.notExists(newPath)) {
                throw new UnsupportedOperationException("This file not exists.");
            } else if (Files.isDirectory(newPath)) {
                throw new UnsupportedOperationException("This command should be used with files only.");
            } else if (!parameters[1].endsWith(".txt")) {
                throw new UnsupportedOperationException("Extension not supported.");
            }

            new FileReader().read(newPath);

            return path;
        }
    },
    BACK() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("BACK") || commands[0].startsWith("back");
        }

        @Override
        Path execute(Path path) {
            if (path.equals(Paths.get(FileSystem.ROOT))) {
                throw new UnsupportedOperationException("This is already the root directory.");
            }

            return path.getParent();
        }
    },
    OPEN() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("OPEN") || commands[0].startsWith("open");
        }

        @Override
        Path execute(Path path) {
            if (parameters.length == 1) {
                throw new UnsupportedOperationException("It is necessary to inform a directory name.");
            }

            final var newPath = Paths.get(path.toAbsolutePath() + "/" + parameters[1]);

            if (Files.notExists(newPath)) {
                throw new UnsupportedOperationException("This directory not exists.");
            } else if (!Files.isDirectory(newPath)) {
                throw new UnsupportedOperationException("Can not open files, just directories.");
            }

            return newPath;
        }
    },
    DETAIL() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("DETAIL") || commands[0].startsWith("detail");
        }

        @Override
        Path execute(Path path) throws IOException {
            Path newPath;

            if (parameters.length == 1) {
                newPath = path;
            } else {
                newPath = Paths.get(path.toAbsolutePath() + "/" + parameters[1]);
            }

            if (Files.notExists(newPath)) {
                throw new UnsupportedOperationException("This directory or file not exists.");
            }

            final var attributes = Files.getFileAttributeView(newPath, BasicFileAttributeView.class).readAttributes();

            System.out.println("Is directory [%s]".formatted(attributes.isDirectory()));
            System.out.println("Size [%s]".formatted(attributes.size()));
            System.out.println("Created on [%s]".formatted(attributes.creationTime().toInstant()));
            System.out.println("Last acess time [%s]".formatted(attributes.lastAccessTime().toInstant()));

            return path;
        }
    },
    EXIT() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("EXIT") || commands[0].startsWith("exit");
        }

        @Override
        Path execute(Path path) {
            System.out.print("Saindo...");
            return path;
        }

        @Override
        boolean shouldStop() {
            return true;
        }
    };

    abstract Path execute(Path path) throws IOException;

    abstract boolean accept(String command);

    void setParameters(String[] parameters) {
    }

    boolean shouldStop() {
        return false;
    }

    public static Command parseCommand(String commandToParse) {

        if (commandToParse.isBlank()) {
            throw new UnsupportedOperationException("Type something...");
        }

        final var possibleCommands = values();

        for (Command possibleCommand : possibleCommands) {
            if (possibleCommand.accept(commandToParse)) {
                possibleCommand.setParameters(commandToParse.split(" "));
                return possibleCommand;
            }
        }

        throw new UnsupportedOperationException("Can't parse command [%s]".formatted(commandToParse));
    }
}
