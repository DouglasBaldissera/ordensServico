package util;

public class Query
{

    String query;

    char separar = ' ';

    Tipo[] chaves = new Tipo[0];

    private static byte numero = 0;

    private static byte boo = 1;

    private static byte palavra = 2;

    private static byte replace = 5;

    private static byte pln = 3;

    private static byte ple = 4;

    private void removerEspacos(String[] s)
    {
        int quantidade = 0;
        for (int i = 0; i < s.length; i++)
        {
            if (!s[i].isEmpty())
            {
                quantidade++;
            }
        }
        String[] f = new String[quantidade + 1];
        quantidade = 0;
        for (int i = 0; i < s.length; i++)
        {
            if (!s[i].isEmpty())
            {
                f[quantidade] = f[i];
                quantidade++;
            }
        }
        s = f;
    }

    public void maiorIgual(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = boo;
        t.c = 'a';
        add(t);
    }

    public void menorIgual(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = boo;
        t.c = 'e';
        add(t);
    }

    public void maior(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = boo;
        t.c = '>';
        add(t);
    }

    public void menor(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = boo;
        t.c = '<';
        add(t);
    }
    
    public void diferente(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = boo;
        t.c = 'd';
        add(t);
    }

    public void igual(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = boo;
        t.c = '=';
        add(t);
    }

    public void chaveNumero(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = numero;
        add(t);
    }

    public void chavePalavra(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = palavra;
        add(t);
    }

    public void and(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = replace;
        t.c = '1';
        add(t);
    }

    public void or(String chave)
    {
        Tipo t = new Tipo();
        t.chave = chave;
        t.tipo = replace;
        t.c = '2';
        add(t);
    }

    private void add(Tipo c)
    {
        Tipo[] t = new Tipo[chaves.length + 1];
        for (int i = 0; i < chaves.length; i++)
        {
            t[i] = chaves[i];
        }
        t[chaves.length] = c;
        chaves = t;
    }

    private Tipo[] classficiar(String[] s)
    {
        Tipo[] c = new Tipo[s.length];
        for (int i = 0; i < s.length; i++)
        {
            boolean ok = false;
            for (int j = 0; j < chaves.length && !ok; j++)
            {
                if (chaves[j].chave.equals(s[i]))
                {
                    c[i] = chaves[j];
                    ok = true;
                }
            }
            if (!ok)
            {
                c[i] = clpl(s[i]);
            }
        }
        return c;
    }

    private Tipo clpl(String s)
    {
        Tipo t = new Tipo();
        boolean numero = false;
        try
        {
            int v = Integer.parseInt(s);
            numero = true;
        } catch (Exception e)
        {

        }
        t.chave = s;
        if (numero)
        {
            t.tipo = pln;
        } else
        {

            t.tipo = ple;
        }
        return t;
    }

    public String gerar()
    {
        String[] partes = query.split(separar + "");
        removerEspacos(partes);
        Tipo[] tipos = classficiar(partes);
        return montar(tipos);
    }

    private String montar(Tipo[] t)
    {
        if (t.length == 0)
        {
            return "";
        }
        int p = 0;
        Tipo ultimo = t[p];
        String resultado = "";
        if ((ultimo.tipo != numero && ultimo.tipo != palavra) && p < t.length)
        {
            resultado += todos(ultimo);
            p++;
            if (p < t.length && !resultado.isEmpty() && t[p].tipo != replace)
            {
                resultado += " OR ";
            }
        }
        int tentativas = 0;
        int max = t.length + 10;
        while (p < t.length && tentativas != max)
        {
            ultimo = t[p];
            String v = "";
            // System.out.println(ultimo.chave);
            while ((ultimo.tipo != numero && ultimo.tipo != palavra && ultimo.tipo != boo && ultimo.tipo != replace) && p < t.length)
            {
                if (p < t.length && !v.isEmpty())
                {
                    resultado += " OR ";
                }
                v = todos(ultimo);
                resultado += v;
                System.out.println(ultimo.chave + " " + ultimo.tipo + " " + t.length);
                p++;
                if (p < t.length)
                {
                    ultimo = t[p];
                }

            }
            if (p < t.length)
            {
                if (ultimo.tipo == numero && p + 1 < t.length)
                {
                    System.out.println("ee");
                    p++;
                    Tipo n = ultimo;
                    ultimo = t[p];
                    boolean ok = ultimo.tipo == pln || ultimo.tipo == boo;
                    boolean prox = true;
                    boolean re = false;
                    char l = '=';
                    while (ok && p < t.length)
                    {
                        ok = false;
                        if (ultimo.tipo == boo && prox)
                        {
                            System.out.println("kke" + p);
                            l = ultimo.c;
                            prox = false;
                            p++;
                            if (p < t.length)
                            {
                                ultimo = t[p];
                                ok = ultimo.tipo == pln || ultimo.tipo == boo;
                            } else
                            {
                                ok = false;
                            }
                        }
                        while (ultimo.tipo == pln && p < t.length)
                        {
                            if (re)
                            {
                                resultado += " OR ";
                            }
                            resultado += n.chave + " " + comp(l) + " " + ultimo.chave;
                            re = true;
                            prox = true;
                            p++;
                            if (p < t.length)
                            {
                                ultimo = t[p];
                                ok = ultimo.tipo == pln || ultimo.tipo == boo;
                            } else
                            {
                                ok = false;
                            }
                        }
                    }
                }
            }
            if (p < t.length)
            {
                if (ultimo.tipo == palavra && p + 1 < t.length)
                {
                    p++;
                    Tipo n = ultimo;
                    ultimo = t[p];
                    boolean ok = ultimo.tipo == pln || ultimo.tipo == boo || ultimo.tipo == ple;
                    boolean prox = true;
                    boolean re = false;
                    char l = '=';
                    while (ok && p < t.length)
                    {
                        ok = false;
                        if (ultimo.tipo == boo && prox)
                        {
                            if (ultimo.c == '=')
                            {
                                l = ultimo.c;
                                prox = false;
                                p++;
                                if (p < t.length)
                                {
                                    ultimo = t[p];
                                    ok = ultimo.tipo == pln || ultimo.tipo == boo || ultimo.tipo == ple;
                                } else
                                {
                                    ok = false;
                                }
                            }
                        }
                        while (ultimo.tipo == pln && p < t.length)
                        {
                            if (re)
                            {
                                resultado += " OR ";
                            }
                            resultado += n.chave + " " + comp(l) + " '" + ultimo.chave+"'";
                            re = true;
                            prox = true;
                            p++;
                            if (p < t.length)
                            {
                                ultimo = t[p];
                                ok = ultimo.tipo == pln || ultimo.tipo == boo || ultimo.tipo == ple;
                            } else
                            {
                                ok = false;
                            }
                        }
                    }
                }
            }
            if (ultimo.tipo == replace)
            {
                p++;
                resultado += " " + re(ultimo.c) + " ";
            }
            tentativas++;
            if(tentativas == max)
            {
                return "";
            }
        }

        return resultado;
    }

    private String comp(char c)
    {
        if (c == '>')
        {
            return ">";
        }
        if (c == '<')
        {
            return "<";
        }
        if (c == '=')
        {
            return "=";
        }
        if (c == 'a')
        {
            return ">=";
        }
        if (c == 'e')
        {
            return "<=";
        }
        
        if(c == 'd')
        {
            return "!=";
        }

        return "";
    }

    private String re(char c)
    {
        if (c == '1')
        {
            return "AND";
        }
        return "OR";
    }

    private String todos(Tipo t)
    {
        String r = "";
        boolean ok;
        for (int j = 0; j < chaves.length; j++)
        {
            ok = false;
            if (chaves[j].tipo == numero)
            {
                if (t.tipo == pln)
                {
                    r += chaves[j].chave + " = " + t.chave;
                    ok = true;
                }
            }

            if (chaves[j].tipo == palavra)
            {
                r += chaves[j].chave + " ILIKE '%" + t.chave + "%'";
                ok = true;
            }

            if (chaves.length - 1 > j && ok)
            {
                r += " OR ";
            }
        }
        return r;
    }

    public Query(String query)
    {
        this.query = query;
        adicionar();
    }

    public void adicionar()
    {
        maior(">");
        maior("maior");
        menor("<");
        menor("menor");
        igual("=");
        igual("igual");
        menorIgual("<=");
        menorIgual("menorigual");
        maiorIgual(">=");
        maiorIgual("maiorigual");
        and("e");
        or("ou");
        or(",");
        diferente("!=");
        diferente("diferente");
    }

    private static class Tipo
    {

        String chave;

        byte tipo;

        char c = ' ';

        public Tipo()
        {

        }
    }

}
