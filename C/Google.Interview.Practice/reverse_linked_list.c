#include <stdio.h>
#include <stdlib.h>

// defined constants
#define MAX_ITEMS 5

// structure for the linked list
struct Node {
  int num;
  struct Node *next;
};

// function prototypes
struct Node *reverse_recursive(struct Node *ptr, struct Node *previous);
struct Node *reverse_iterative(struct Node *ptr);

//
// program entry point
//
int main(int argc, char *argv[])
{
  struct Node *head = (struct Node *)malloc(sizeof(struct Node));
  struct Node *tail = head;
  struct Node *reversed_head = NULL;

  // create and populate the linked list
  for (int i = 1; i < MAX_ITEMS; i++) {
    tail->num = i;
    tail->next = (struct Node *)malloc(sizeof(struct Node));
    tail = tail->next;
  }
  // fill in the last "num" field with a value since it wasn't
  // filled in during the loop
  tail->num = MAX_ITEMS;

  printf("initial list values:\n");
  tail = head;
  while (tail != NULL) {
    printf("num: %d\n", tail->num);
    tail = tail->next;
  }

  // recursively reverse the list
  reversed_head = reverse_recursive(head, NULL);
  tail = reversed_head;
  printf("\n\nreversed recursively:\n");
  while (tail != NULL) {
    printf("num: %d\n", tail->num);
    tail = tail->next;
  }

  // iteratively reverse the list
  reversed_head = reverse_iterative(reversed_head);
  tail = reversed_head;
  printf("\n\nreversed recursively:\n");
  while (tail != NULL) {
    printf("num: %d\n", tail->num);
    tail = tail->next;
  }

  printf("\ncleanup, free linked list nodes\n");
  tail = reversed_head;
  while (tail != NULL) {
    struct Node *temp = tail;
    tail = tail->next;
    free(temp);
    temp = NULL;
  }

  return 0;
}


//
// Recursively reverse a linked list
//
struct Node *reverse_recursive(struct Node *ptr, struct Node *previous)
{
  struct Node *temp = NULL;
  if (ptr->next == NULL) {
    // end of list
    ptr->next = previous;
    return ptr;
  } else {
    // not end of list
    temp = reverse_recursive(ptr->next, ptr);
    ptr->next = previous;
    return temp;
  }
}


//
// Iteratively reverse a linked list
//
struct Node *reverse_iterative(struct Node *ptr)
{
  struct Node *temp = NULL;
  struct Node *previous = NULL;

  while (ptr != NULL) {
    temp = ptr->next;
    ptr->next = previous;
    previous = ptr;
    ptr = temp;
  }

  return previous;
}
