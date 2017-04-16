package src;

public class Aexp {

    private enum AexpType {
        INTEGER,
        ID,
        EXP
    }
    
    private final AexpType eType;
    private Integer inum;
    private String id;
    private Args operands;
    private int operator;

    Aexp(Integer x) {
        eType = AexpType.INTEGER;
        inum = x;        
    }

    Aexp(String x) {
        eType = AexpType.ID;
        id = x;        
    }

    Aexp(Args x, int op) {
        eType = AexpType.EXP;
        operands = x;
        operator = op;
    }

    public String getexp() {

        String s = "";
        switch (this.eType) {
            case INTEGER: s = "" + inum; break;
            case ID: s = id; break;
            case EXP:
                switch (operator) {
                    case sym.PLUS:
                        s = "PLUS(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.MINUS:
                        s = "MINUS(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.TIMES:
                        s = "TIMES(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.DIVIDE:
                        s = "DIVIDE(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    default: break;
                } break;
            default: break;
        }

        return s;
    }

    public int getValue() {
        Integer val = 0;
        switch (this.eType) {
            case INTEGER:
                // expression is a number
                val = inum; break;
            case ID:
                //expression is a variable
                val = SymbolTable.getValue(id);
                if (val == null) {
                    System.out.print(id + " was not declared");
                    System.exit(0);
                }   break;
            case EXP:
                //expression is a math expression
                switch (operator) {
                    case sym.PLUS:
                        val = operands.getfi().getValue() + operands.getse().getValue();
                        break;
                    case sym.MINUS:
                        val = operands.getfi().getValue() - operands.getse().getValue();
                        break;
                    case sym.TIMES:
                        val = operands.getfi().getValue() * operands.getse().getValue();
                        break;
                    case sym.DIVIDE:
                        val = operands.getfi().getValue() / operands.getse().getValue();
                        break;
                    default:
                        break;
                } break;
            default: break;
        }
        return val;
    }
}
