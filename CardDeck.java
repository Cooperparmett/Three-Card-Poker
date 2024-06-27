import java.util.Random;

public class CardDeck 
{
    private Card[] deckOfCards;     //Declare the array to store the deck of cards  

    public CardDeck()
    {
        int count = 0;
        int suitCount = 1;          //Suits: 1=Clubs, 2=Diamonds, 3=Hearts, 4=Spades

        deckOfCards = new Card[52];

        for(int i = 0; i < 52; i++)
        {
            deckOfCards[i] = new Card();
        }
        // 2s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(2);
            count++;
        }
        suitCount = 1;

        // 3s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(3);
            count++;
        }
        suitCount = 1;
        // 4s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(4);
            count++;
        }
        suitCount = 1;
        // 5s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(5);
            count++;
        }
        suitCount = 1;

        // 6s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(6);
            count++;
        }
        suitCount = 1;

        // 7s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(7);
            count++;
        }
        suitCount = 1;

        // 8s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(8);
            count++;
        }
        suitCount = 1;

        // 9s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(9);
            count++;
        }
        suitCount = 1;

        // 10s
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(10);
            count++;
        }
        suitCount = 1;

        // Js
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(11);
            count++;
        }
        suitCount = 1;

        // Qs
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(12);
            count++;
        }
        suitCount = 1;

        // Ks
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(13);
            count++;
        }
        suitCount = 1;

        // As
        for(int i = 0; i < 4; i++)
        {
            deckOfCards[count].setSuit(suitCount);
            suitCount++;
            deckOfCards[count].setValue(14);
            count++;
        }
        suitCount = 1;

    }
    public Card[] randomCard()
    {
        Random r = new Random();
        int[] usedCards = new int[6];
        Card[] hands = new Card[6];
        for(int i = 0; i < usedCards.length; i++)
        {
            usedCards[i] = 52;
        }
        int val = 0;        //random number for index
        boolean found = false;
        for(int i = 0; i < 6; i++)
        {
            val = r.nextInt(52);
            for(int j = 0; j < 6; j++)
            {
                if(usedCards[j] == val)
                {
                    found = true;
                }            
            }
            usedCards[i] = val;
            if(found)
            {
                i--;
                found = false;
            }
        }
        for(int i = 0; i < 6; i++)
        {
            hands[i] = deckOfCards[usedCards[i]];
        }
        
        return hands;
    }
}


