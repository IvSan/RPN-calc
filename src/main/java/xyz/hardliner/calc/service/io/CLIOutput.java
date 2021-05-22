package xyz.hardliner.calc.service.io;

public class CLIOutput implements OutputProvider {

    @Override
    public void outputLine(String line) {
        System.out.println(line);
    }

}
