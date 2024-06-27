public class Card implements Comparable<Card>
{
    private int suit;
    private int value;

    public Card()
    {
        
    }

    public void setSuit(int s)
    {
        suit = s;
    }

    public void setValue(int v)
    {
        value = v;
    }

    public int getSuit()
    {
        return suit;
    }

    public int getValue()
    {
        return value;
    }
  
    @Override
    public int compareTo(Card c)
    {
        return (int)(value - c.getValue());
    }
}
