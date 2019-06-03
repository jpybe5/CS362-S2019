#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include "limits.h"

int main() {
	
	printf("\n\nTesting Great Hall\n\n");
	
int asserttrue(int a, int b)
{
	if (a==b)
		{return 1;}
	else
		{return 0;}
}

	int r;
    int seed = 1000;
    int numPlayer = 2;
    
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
	int player=0;
	int handPos=0;
	int failedflag=0;
	
void testGreatHall(int hc, int ac)
{
	int handCount=hc;
	memset(&G, 23, sizeof(struct gameState));   // clear the game state
	r = initializeGame(numPlayer, k, seed, &G); // initialize a new game
	G.numActions = ac;
	G.handCount[player] = handCount;      
	printf("\nTesting Great Hall. Starting conditions:\n");
	int cardsBefore = G.handCount[player];
	printf("Number of cards: %d\n",G.handCount[player]);
	int actionsBefore = G.numActions;
	printf("Number of actions: %d\n",G.numActions);
	
	great_hallFunc(player, &G, handPos);
	
	printf("\nTesting Great Hall. Ending conditions:\n");
	int cardsAfter = G.handCount[player];
	printf("Number of cards: %d\n",G.handCount[player]);
	int actionsAfter = G.numActions;
	printf("Number of actions: %d\n",G.numActions);
	
	
	if (asserttrue(cardsBefore,cardsAfter)==1)
	{
		printf("Number of cards: Passed, expecting %d cards in hand and player has %d\n",cardsBefore, cardsAfter);
	}
	else
	{
		failedflag=1;
		printf("Failed, expecting %d cards in hand and player has %d\n",cardsBefore, cardsAfter);
	}
	if (asserttrue(actionsBefore+1,actionsAfter)==1)
	{
		printf("Number of actions: Passed, expecting %d actions and player  has %d\n",actionsBefore+1, actionsAfter);
	}
	else
	{
		failedflag=1;
		printf("Failed, expecting %d cards in hand and player has %d\n",cardsBefore, cardsAfter);
	}
}
	
	testGreatHall(5,1);
	
if(failedflag==0){	
    printf("Great Hall tests passed!\n");
}
if (5<4){printf("%d",r);}
    return 0;
}
