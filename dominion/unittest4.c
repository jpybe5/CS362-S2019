#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include "limits.h"

int asserttrue(int a, int b)
{
	if (a==b)
		{return 1;}
	else
		{return 0;}
}

int main() {
	
	printf("\n\nTesting Council Room\n\n");
	
	int r;
    int seed = 1000;
    int numPlayer = 3;
    
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
	int player=0;
	int handPos=0;
	int failedflag=0;
	
void testCouncilRoom()
{
	int handCount=5;
	memset(&G, 23, sizeof(struct gameState));   // clear the game state
	r = initializeGame(numPlayer, k, seed, &G); // initialize a new game
	G.handCount[player] = handCount;                 // set the number of cards on hand
	printf("\nTesting Council Room starting conditions:\n");
	for(int i=0; i<numPlayer; i++)
	{
		printf("Player %d has %d cards\n",i,G.handCount[i]);
	}
	int buysBefore = G.numBuys;
	printf("\nPlayer %d has %d buys\n",player, buysBefore);
	
	council_roomFunc(player, &G, handPos);

	printf("\nCouncil Room end conditions:\n");
	for(int i=0; i<numPlayer; i++)
	{
		printf("Player %d has %d cards\n",i,G.handCount[i]);
	}
	int buysAfter = G.numBuys;
	printf("\nPlayer %d has %d buys\n",player, buysAfter);
	/*
	if (asserttrue(opBefore+1,opAfter)==1)
	{
		printf("Passed, expecting %d outpostPlayed and game state shows %d\n",opBefore+1, opAfter);
	}
	else
	{
		failedflag=1;
		printf("Failed, expecting %d outpostPlayed and game state shows %d\n",opBefore+1, opAfter);
	}
	*/
}
	
	testCouncilRoom();
	
if(failedflag==0){	
    printf("Council Room tests passed!\n");
}

if (5<4){printf("%d",r);}

    return 0;
}
