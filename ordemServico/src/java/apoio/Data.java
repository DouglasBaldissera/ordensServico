package apoio;



import java.util.Calendar;


public class Data
{

    public final static int[] diasDosMeses =
    {
        31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public static int defaultDia = 0;
    public static int defaultMes = 0;
    public static int defaultAno = 0;

    private int dia = defaultDia;
    private int mes = defaultMes;
    private int ano = defaultAno;

    public Data(int dia, int mes, int ano)
    {
        this.definirAno(ano);
        this.definirMes(mes);
        this.definirDia(dia);
    }

    public static boolean anoBissexto(int ano)
    {
        return ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;
    }

    public int obterDia()
    {
        return dia;
    }
    
    public boolean entre(Data dataIni, Data dataFim)
    {
        boolean ok = false;
        ok = (igual(dataIni) || maior(dataIni)) && (igual(dataFim) || menor(dataFim));
        return ok;
    }

    public void definirDia(int dia)
    {
        if (validarDia(dia, mes))
        {
            this.dia = dia;
        }
    }

    public int obterMes()
    {
        return mes;
    }

    public void definirMes(int mes)
    {
        if (validarMes(mes))
        {
            this.mes = mes;
        }
    }

    public int obterAno()
    {
        return ano;
    }

    public void definirAno(int ano)
    {
        this.ano = ano;
    }

    public void aumentarDia()
    {
        if (validarDia(obterDia() + 1, mes))
        {
            definirDia(obterDia() + 1);
        } else
        {
            definirDia(1);
            if (validarMes(obterMes() + 1))
            {
                definirMes(obterMes() + 1);
            } else
            {
                definirMes(1);
                definirAno(obterAno() + 1);
            }
        }
    }

    private boolean validarDia(int dia, int mes)
    {
        boolean ok = dia > 0 && dia <= diasDosMeses[mes - 1];
        if (mes == 2 && dia == 29)
        {
            ok = anoBissexto(ano);
        }
        return ok;
    }

    public int diaDaSemana()
    {
        int diferenca = (diferenca(new Data(1, 1, 1))) % 7;

        diferenca = (diferenca + 2) % 7;
        if(diferenca == 0)
        {
            diferenca = 7;
        }
        return diferenca;
    }

    private boolean validarMes(int mes)
    {
        return mes > 0 && mes < 13;
    }

    public static boolean validar(String data)
    {
        String[] s = data.split("/");
        boolean ok = true;
        try
        {
            if (s.length == 3)
            {
                int dia = Integer.parseInt(s[0]);
                int mes = Integer.parseInt(s[1]);
                int ano = Integer.parseInt(s[2]);
                Data d = new Data(dia, mes, ano);
                if (ok)
                {
                    ok = d.obterDia() != 0;
                }
                if (ok)
                {
                    ok = d.obterMes() != 0;
                }
                if (ok)
                {
                    ok = d.obterAno() != 0;
                }
            }
        } catch (Exception e)
        {
            ok = false;
        }
        return ok;
    }

    public static Data criar(String data)
    {
        String[] s = data.split("/");
        Data d = null;
        try
        {
            if (s.length == 3)
            {
                int dia = Integer.parseInt(s[0]);
                int mes = Integer.parseInt(s[1]);
                int ano = Integer.parseInt(s[2]);
                d = new Data(dia, mes, ano);
            }
        } catch (Exception e)
        {

        }
        return d;
    }

    public int diferenca(Data data)
    {
        Data data1, data2;
        if (this.maior(data))
        {
            data1 = this;
            data2 = new Data(data.obterDia(), data.obterMes(), data.obterAno());
        } else
        {
            data1 = data;
            data2 = new Data(obterDia(), obterMes(), obterAno());
        }
        int count = 0;
        while (data1.maior(data2))
        {
            data2.aumentarDia();
            count++;
        }
        return count;
    }

    public static Data criarDataAtual()
    {
        Calendar c = Calendar.getInstance();
        Data data = new Data(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
        return data;
    }

    public static boolean validar(Data data)
    {
        boolean ok = data.obterDia() != 0 && data.obterMes() != 0 && data.obterAno() != 0;
        return ok;
    }

    public boolean menor(Data data)
    {
        boolean ok = validar(this) && validar(data);
        if (ok)
        {
            if (obterAno() > data.obterAno())
            {
                ok = false;
            } else if (obterAno() == data.obterAno())
            {
                if (obterMes() > data.obterMes())
                {
                    ok = false;
                } else if (obterMes() == data.obterMes())
                {
                    if (obterDia() > data.obterDia())
                    {
                        ok = false;
                    } else if (obterDia() == data.obterDia())
                    {
                        ok = false;
                    }
                }
            }
        }
        return ok;
    }

    public boolean maior(Data data)
    {
        boolean ok = validar(this) && validar(data);
        if (ok)
        {
            if (obterAno() < data.obterAno())
            {
                ok = false;
            } else if (obterAno() == data.obterAno())
            {
                if (obterMes() < data.obterMes())
                {
                    ok = false;
                } else if (obterMes() == data.obterMes())
                {
                    if (obterDia() < data.obterDia())
                    {
                        ok = false;
                    } else if (obterDia() == data.obterDia())
                    {
                        ok = false;
                    }
                }
            }
        }
        return ok;
    }

    public boolean igual(Data data)
    {
        boolean ok = validar(this) && validar(data);
        if (ok)
        {
            ok = obterDia() == data.obterDia() && obterMes() == data.obterMes() && obterAno() == data.obterAno();
        }
        return ok;
    }

    public String dataFormatada()
    {
        String data = formatarNumero(dia);
        data += "/" + formatarNumero(mes);
        data += "/" + formatarNumero(ano);
        return data;
    }

    public String dataNumero()
    {
        String data = formatarNumero(dia);
        data += formatarNumero(mes);
        data += formatarNumero(ano);
        return data;
    }
    
    private String formatarNumero(int numero)
    {
        if(numero < 10)
        {
            return "0"+numero;
        }
        else
        {
            return ""+numero;
        }
    }
}
