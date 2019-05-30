#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <time.h>
#include "rngs.h"
#include "limits.h"
#include <stdlib.h>

int asserttrue(int a, int b)
{
	if (a==b)
		{return 1;}
	else
		{return 0;}
}

int playerTreasureCards(int currentPlayer, struct gameState *state){
	
	int treasureCount=0;
	for (int i=0; i<state->handCount[currentPlayer]; i++){
		int enumVal = handCard(i,state);
		//count copper, silver, gold
		if (enumVal==4 || enumVal==5 || enumVal==6){
			treasureCount++;
		}
	}
	return treasureCount;	
}

void testAdventurerRandom()
{
	int r;
    int seed = 1000;
    int numPlayer = 2;
    int testresult;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
	srand(time(NULL));
	//////////////////////////////
	int testCount=0;
	int player=0;
	for (testCount=0; testCount<21; testCount++){
		
		memset(&G, 23, sizeof(struct gameState));   // clear the game state
		r = initializeGame(numPlayer, k, seed, &G); // initialize a new game
		G.handCount[player] = 5;
		G.deckCount[player] = (rand() % 20)+5;	
		
		//give player 5 random cards
		for (int i=0; i<5; i++)
		{
			G.hand[player][i]=rand()%27;
		}
		
		
		printf("\n\nTest #%d\nDeck count of %d\n", testCount, G.deckCount[player]);
		int cardsBefore = G.handCount[player];
		printf("Player has %d cards\n",G.handCount[player]);
		printf("Player has %d treasure cards\n",playerTreasureCards(player,&G));
			
		printf("Playing Adventurer\n");
		testresult = adventurerFunc(player, &G);
		int cardsAfter = G.handCount[player];
		printf("Player now has %d cards\n",G.handCount[player]);
		printf("Player has %d treasure cards\n",playerTreasureCards(player,&G));
		int assertionNum = asserttrue(cardsBefore+2,cardsAfter);
		if(assertionNum==1){
			printf("2 treasure cards added, Success!\n");
		}
		else{
			printf("Less treasure 2 cards added\n");
		}
	}
	if(6>7){
		testresult++;
		r++;
	}
	

}

int main() {
	
	printf("\n\nTesting Adventurer\n\n");
	
	
	
	testAdventurerRandom();
}



	
