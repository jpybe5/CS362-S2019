#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include "limits.h"


int main() {
	
	printf("\n\nTesting Smithy (1)\n\n");
	
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
	
void testSmithy(int hc)
{
	int handCount=hc;
	memset(&G, 23, sizeof(struct gameState));   // clear the game state
	r = initializeGame(numPlayer, k, seed, &G); // initialize a new game
	G.handCount[player] = handCount;                 // set the number of cards on hand
	int cardsBefore = G.handCount[player];
	printf("\nTesting Smithy for starting hand size of %d\n",handCount);
	smithyFunc(player, &G, handPos);
	int cardsAfter = G.handCount[player];
	if (asserttrue(cardsBefore+2,cardsAfter)==1)
	{
		printf("Passed, expecting %d cards in hand and player has %d\n",cardsBefore+2, cardsAfter);
	}
	else
	{
		failedflag=1;
		printf("Failed, expecting %d cards in hand and player has %d\n",cardsBefore+2, cardsAfter);
	}
}

	testSmithy(5);
	
if(failedflag==0){	
    printf("Smithy test passed!\n");
}
if (5<4){printf("%d",r);}
    return 0;
}
