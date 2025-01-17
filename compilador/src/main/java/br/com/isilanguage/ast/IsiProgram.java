package br.com.isilanguage.ast;

import br.com.isilanguage.datastructures.IsiSymbol;
import br.com.isilanguage.datastructures.IsiSymbolTable;
import br.com.isilanguage.utils.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IsiProgram {
    private IsiSymbolTable varTable;
    private ArrayList<AbstractCommand> commands;
    private String programName;
    
    public void generateTarget() {
        
        String codeCpp = generateCodeInCpp();
        String codeJava = generateCodeInJava();
        try 
        {
            WriteCodeInFile(codeCpp, "cpp");
            WriteCodeInFile(codeJava, "java");
        }
        catch (IOException ex)
        {
            System.out.println("ERROR: Write Code In File. " + ex.getMessage());
        }
    }
    
    private String generateCodeInCpp()
    {
        StringBuilder str = new StringBuilder();
        str.append("#include <iostream>\n")
           .append("using namespace std;\n");
        str.append("int main(){\n");
        for (IsiSymbol symbol: varTable.getAll())
        {
            str.append("\t").append(symbol.generateCodeInCpp()).append("\n");
        }
        
        for (AbstractCommand command: commands)
        {
            str.append("\t").append(command.generateCodeInCpp()).append("\n");    
        }
        str.append("}");
        
        return str.toString();
    }
    
    private String generateCodeInJava()
    {
        StringBuilder str = new StringBuilder();
        
        boolean existsLeitura = Util.existCommand(commands, CommandType.LEITURA);
        if (existsLeitura)
            str.append("import java.util.Scanner;\n");
        
        str.append("public class Program {\n")
            .append("\tpublic static void main(String[] args) {\n");
        
        
        String tabs = Util.getTabs(2);
        if (existsLeitura)
            str.append(tabs).append("Scanner scanner = new Scanner(System.in);\n");
        
        for (IsiSymbol symbol: varTable.getAll())
        {
            str.append(tabs).append(symbol.generateCodeInJava()).append("\n");
        }
        
        for (AbstractCommand command: commands)
        {
            str.append(tabs).append(command.generateCodeInJava()).append("\n");    
        }
        
        str.append("\t}\r}");
        
        return str.toString();
    }
    
    private static void WriteCodeInFile(String code, String extension) throws IOException
    {
        try (FileWriter fr = new FileWriter(new File("main." + extension))) {
                fr.write(code);
            }
    }

    public IsiSymbolTable getVarTable() {
        return varTable;
    }

    public void setVarTable(IsiSymbolTable varTable) {
        this.varTable = varTable;
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<AbstractCommand> commands) {
        this.commands = commands;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
