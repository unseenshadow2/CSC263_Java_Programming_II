/**
 * Created by unseenshadow2 on 10/22/2016.
 */
public enum ASCIICharacter
{
    U(new String[]
    {   "||  ||",
        "||  ||",
        " \\\\// "}),
    A(new String[]
    {   "  //\\\\  ",
        " //__\\\\ ",
        "//    \\\\"}),
    T(new String[]
    {   "---||---",
        "   ||   ",
        "   ||   "});

    private String[] value;

    ASCIICharacter(String[] val) { value = val; }
    public String[] getValue() { return value; }
}
