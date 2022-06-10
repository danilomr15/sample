package br.com.danilomr.java11.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Java11NewFeaturesTest {

    @Test
    @DisplayName("New String Methods")
    /**
     * lines() - quebra automaticamente o \n em linhas;
     * strip - funciona como o trim, porém remove todos os caracteres unicode que geram espaços em branco
     */
    public void test1() {
        String multilineString = "Baeldung helps \n \n developers \n explore Java.";
        List<String> lines = multilineString.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());
        assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");
    }

    @Test
    @DisplayName("New File Methods")
    /**
     * Files.writeString - Facilita a criação de um arquivo de texto
     * Files.readString - Facilita a leitura de um arquivo de texto
     */
    public void test2() throws IOException {
        final Path tempDir = Files.createTempDirectory("tmp").toAbsolutePath();
        Path filePath = Files.writeString(Files.createTempFile(tempDir, "demo", ".txt"), "Sample text");
        String fileContent = Files.readString(filePath);
        assertThat(fileContent).isEqualTo("Sample text");
    }

    @Test
    @DisplayName("Collection to Array")
    /**
     * toArray - Novo método da interface Collection que transforma facilmente uma collection em um array
     */
    public void test3() {
        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String[] sampleArray = sampleList.toArray(String[]::new);
        assertThat(sampleArray).containsExactly("Java", "Kotlin");
    }

    @Test
    @DisplayName("The Not Predicate Method")
    /**
     * Predicate.not - Novo método que valida uma negação de um equals (!equals)
     */
    public void test4() {
        List<String> sampleList = Arrays.asList("Java", "\n \n", "Kotlin", " ");
        List withoutBlanks = sampleList.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        assertThat(withoutBlanks).containsExactly("Java", "Kotlin");
    }

    @Test
    @DisplayName("Local-Variable Syntax for Lambda")
    /**
     * Além da criação da palavra chave 'var' para declarar uma variável sem a necessidade de
     * explicitar o tipo dessa variável, em uma lambda é possível agora anotas essas variáveis
     * conforme a validação necessária no fluxo.
     */
    public void test5() {
        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String resultString = sampleList.stream()
                .map((@Nonnull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        assertThat(resultString).isEqualTo("JAVA, KOTLIN");
    }

    @Test
    @DisplayName("Nest Based Access Control")
    /**
     * Nestmates são classes alinhadas, ou seja, uma inner class é nestmate de onde ela foi implementada.
     * É possível que as nestmates acessem métodos privados entre si, o que até então não era permitido.
     */
    public void test6() {
        assertThat(TestNestMates.InnerTestNestMates.class.getNestHost()).isEqualTo(TestNestMates.class);
        assertThat(TestNestMates.class.isNestmateOf(TestNestMates.InnerTestNestMates.class)).isTrue();
        Set<String> nestedMembers = Arrays.stream(TestNestMates.InnerTestNestMates.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.toSet());
        assertThat(nestedMembers).contains(TestNestMates.class.getName(), TestNestMates.InnerTestNestMates.class.getName());
    }

    @Test
    @DisplayName("Running Java Files")
    /**
     * Agora não é mais necessário rodar o javac para compilar uma classe, podemos rodar direto
     * um comando 'java heelo.java'
     */
    public void test7() throws IOException {
        final String java11Path = "/opt/java/jdk-11.0.13/bin/java";
        final File helloFile = new File("src/test/resources/hello.java");
        final ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(java11Path, helloFile.getAbsolutePath());

        final Process process = processBuilder.start();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            assertThat(line).isEqualTo("Hello Java 11");
        }
        reader.close();
    }

    @Test
    @DisplayName("Stream of Nullable")
    /**
     * Stream.ofNullable - Cria uma stream de um só elemento, caso seja null,
     * retrna uma stream vazia
     */
    public void test8() {
        final List<String> list = Arrays.asList("One", "Two", "Three");
        assertThat(Stream.ofNullable(list).count()).isEqualTo(1L);
        assertThat(Stream.ofNullable("One").count()).isEqualTo(1L);
        assertThat(Stream.ofNullable(null).count()).isEqualTo(0L);
    }

    @Test
    @DisplayName("Stream dropwhile e takewhile")
    /**
     * dropwhile - Quando o Predicate retornar true, considera o restante dos valores da stream;
     * takewhile - Considera os valores da stream antes que o Predicate retorne true, e então abandona a stream;
     */
    public void test9() {
        List<Integer> listDrop = Stream.of(1, 2, 3, 2, 1)
                .dropWhile(n -> n < 3)
                .collect(Collectors.toList()); // [3, 2 ,1]
        assertThat(listDrop.containsAll(Arrays.asList(3, 2, 1))).isTrue();

        List<Integer> listTake = Stream.of(1, 2, 3, 2, 1)
                .takeWhile(n -> n < 3)
                .collect(Collectors.toList());  // [1, 2]
        assertThat(listTake.containsAll(Arrays.asList(1, 2))).isTrue();
    }

    @Test
    @DisplayName("Optional or() - JAVA 9")
    /**
     * Retorna um outro Optional caso o valor do isPresent do primeiro seja false;
     */
    public void test10() {
        String expected = "properValue";
        Optional<String> value = Optional.of(expected);
        Optional<String> defaultValue = Optional.of("default");

        Optional<String> result = value.or(() -> defaultValue);
        assertThat(result.get()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Optional oifPresentOrElse() - JAVA 9")
    /**
     * Se o ifPresent retorna true, processa o primeiro argumento, senão, o segundo argumento é processado
     */
    public void test11() {
        Optional<String> value = Optional.of("properValue");
        AtomicInteger successCounter = new AtomicInteger(0);
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

        value.ifPresentOrElse(v -> successCounter.incrementAndGet(), onEmptyOptionalCounter::incrementAndGet);

        assertThat(successCounter.get()).isEqualTo(1);
        assertThat(onEmptyOptionalCounter.get()).isEqualTo(0);
    }

    @Test
    @DisplayName("Optional stream() - JAVA 9")
    /**
     * Cria uma stream a partir de um optional
     */
    public void test12() {
        Optional<String> value = Optional.of("a");

        List<String> collect = value.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertThat(collect).hasSameElementsAs(List.of("A"));
    }

    @Test
    @DisplayName("Optional isEmpty() e isPresent()")
    /**
     * Verifica se um Optional é vazio, em contrapartida, o isPresent() verifica se
     * um Optional NÃO está vazio
     */
    public void test13() {
        String name = null;

        assertThat(Optional.ofNullable(name).isPresent()).isFalse();
        assertThat(Optional.ofNullable(name).isEmpty()).isTrue();

        name = "Joe";
        assertThat(Optional.ofNullable(name).isPresent()).isTrue();
        assertThat(Optional.ofNullable(name).isEmpty()).isFalse();
    }

    @Test
    @DisplayName("String repeat()")
    /**
     * Repete a String a quantidade de vezes informada no parâmetro
     */
    public void test14() {
        final String equal = "=";
        assertThat(equal.repeat(5)).isEqualTo("=====");
    }

    @Test
    @DisplayName("String stripLeading(), stripTrailling e isBlank()")
    /**
     * stripLeading - Exclui todos os caracteres em branco do início da String, inclusive unicode;
     * stripTrailing - Exclui todos os caracteres em branco do fim da String, inclusive unicode;
     * isBlank - Verifica se a String é vazia, desconsiderando qualquer tipo de caractere em branco, inclusive os unicode;
     */
    public void test15() {
        final String equal = "     =     ";
        assertThat(equal.stripLeading()).isEqualTo("=     ");
        assertThat(equal.stripTrailing()).isEqualTo("     =");

        final String blank = "    ";
        assertThat(blank.isBlank()).isTrue();
    }

    @Test
    @DisplayName("Transferência de dados de um InputStream para um OutputStream")
    /**
     * O método transferTo transfere facilmente os dados de um InputStream para
     * um OutputStream e retorna o número de bytes transferidos
     */
    public void test16() throws IOException {
        var classLoader = ClassLoader.getSystemClassLoader();
        var inputStream = classLoader.getResourceAsStream("myFile.txt");
        var tempFile = File.createTempFile("myFileCopy", "txt");
        try (var outputStream = new FileOutputStream(tempFile)) {
            final long bytes = inputStream.transferTo(outputStream);
            assertThat(bytes).isEqualTo(8L);
            assertThat(outputStream).isNotNull();
        }
    }
}
