// Templated for extra credit

public class LinkedList<TemplateType>
{
    // Linked list contains size, and head which points to first node, and tail which points to the last node
    private int size;
    private Node<TemplateType> head;
    private Node<TemplateType> tail;
    
    // Constructor sets up an empty LinkedList
    public LinkedList()
    {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }
    
    /**
     * Adds a node with value given to front of linked list
     * @param data
     */
    public void addToFront(TemplateType data)
    {
        // Creates a new node
        Node<TemplateType> createdNode = new Node<TemplateType>();
        
        // Sets data of node passing in argument into parameter
        createdNode.setData(data);
        
        // Sets the new nodes next to head and makes head the new node
        createdNode.setNext(head);
        head = createdNode;
        
        // If this is the first node sets tail to the new node
        if (size == 0)
            tail = head;
        
        // Increases size of List
        size++;
    }
    
    /**
     * Adds node to end of linked list
     * @param data
     */
    public void addToEnd(TemplateType data)
    {
        // Creates a new node
        Node<TemplateType> createdNode = new Node<TemplateType>();
        // Sets data of node passing in argument into parameter        
        createdNode.setData(data);
        
        // Sets next of node to null 
        createdNode.setNext(null);
        
        // If this is the first node, makes head and tail point to new node
        if (size == 0)
        {
            head = createdNode;
            tail = createdNode;
        }
        // If not only node, sets tail node to point to new node and sets new tail
        else
        {
            tail.setNext(createdNode);
            tail = createdNode;
        }

        // Increases size of linked list
        size++;
    }
    
    /**
     * Inserts node at specific index. If linked list is empty, cannot be inserted other than at 0 index.
     * Cannot insert at a negative index. Index has to be between 0 and the size of the linked list. 
     * Throws IndexOutOfBoundsException if error case occurs
     * @param data: value of new node
     * @param index: where to insert new node
     */
    public void addAtIndex(TemplateType data, int index)
    {      
        // Throws exception if linked list is empty, index can only be 0
        if (size == 0 && index > 0)
            throw new IndexOutOfBoundsException("List is currently empty - index must be 0"); 
        
        // Throws exception if index is bigger than the linked list is negative
        else if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + size); 
        
        // If node can be added
        else
        {
            // Add to front if index is 0, add to end if index is size
            if (index == 0)
                addToFront(data);
            else if (index == size)
                addToEnd(data);

            // If index is in middle
            else
            {
                // Starts index count at one and sets current node to second item, and previous node to first item
                int tempNodeIndex = 1;
                Node<TemplateType> tempNodePrevious = head;
                Node<TemplateType> tempNode = head.getNext();
                
                // Creates a new node to be inserted, and sets the data
                Node<TemplateType> newNode = new Node<TemplateType>();
                newNode.setData(data);
                
                // Moves tempNode to where the index is and previous to one behind
                while (tempNodeIndex != index)
                {
                    tempNode = tempNode.getNext();
                    tempNodePrevious = tempNodePrevious.getNext();;
                    tempNodeIndex++;
                }
                
                // Sets previous node next to the new node, and new node to the one current at index
                newNode.setNext(tempNode);
                tempNodePrevious.setNext(newNode);
            }
        }    
    }
    
    /**
     * Deletes the first node in linked list
     */
    public void deleteFront()
    {
        Node<TemplateType> tempNode = head;
        
        // If Linked List is already empty prints
        if (size == 0)
           System.out.println("Empty LinkedList");
        // If not empty
        else
        {
            // Move head to next node and set original head next value to null
            head = head.getNext();
            tempNode.setNext(null);
        }
    }
    
    /**
     * Deletes the last node in the linked list
     */
    public void deleteEnd()
    {
        // If the last node is null, linked list is empty and escapes
        if (tail == null)
            return;
        // If there is at least one node in linked list
        else
        {
            // If there is only one node in linked list
            if (head == tail)
            {
                // Set both head and tail to null
                head = null;
                tail = null;
            }
            // If there is more than one element in linked list
            else
            {
                Node<TemplateType> oneBeforeTail = head;
                // Moves tempNode to one before last
                while (oneBeforeTail.getNext() != tail)
                    oneBeforeTail = oneBeforeTail.getNext();
                // Sets the tail to new tail and points it to null
                tail = oneBeforeTail;
                tail.setNext(null);
            }
        }
        // Decrease size of linked list
        size--;
    }

    /**
     * Deletes node at specific index.
     * Index must be greater than 0 and less than the size, if not will throw IndexOutOfBoundsException
     * If Linked list is empty, will also throw IndexOutOfBoundsException.
     * 
     * @param index : starts at 0 removes node at index position
     */
    public void deleteIndex(int index)
    {
        // Throws exception is linked list is empty
        if (size == 0)
            throw new IndexOutOfBoundsException("List is already empty"); 
        // If index is out of bounds throws exception
        else if (index > size -1 || index < 0)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (size - 1)); 
        
        else
        {
            // Delete front if index is 0, delete end if index is size
            if (index == 0)
                deleteFront();
            else if (index == size - 1)
                deleteEnd();

            // If index is in middle
            else
            {
                int tempNodeIndex = 1;
                Node<TemplateType> tempNodePrevious = head;
                Node<TemplateType> tempNode = head.getNext();
                
                // Makes tempNode where the index is
                while (tempNodeIndex != index)
                {
                    tempNode = tempNode.getNext();
                    tempNodePrevious = tempNodePrevious.getNext();;
                    tempNodeIndex++;
                }
                
                // Sets previous node next to skip next node, and sets null for the node being removed
                tempNodePrevious.setNext(tempNode.getNext());
                tempNode.setNext(null);
            }
        }
    }

    /**
     * Finds and deletes the first node with its data same as the one passed in.
     * @param data : value of node looking for deletion
     * @return : returns true if node was deleted or false if no noes were deleted
     */
    public boolean deleteFirst(TemplateType data)
    {
        Node<TemplateType> tempNode = head;
        Node<TemplateType> previousNode = null;

        // Returns false if linked list is empty
        if (tempNode == null)
            return false;

        // Check first node
        if (tempNode.getData().equals(data))
        {
            // Move head to next node and set original head next value to null
            head = head.getNext();
            tempNode.setNext(null);
            // Decrease size of linked list
            size--;

            return true;
        }

        // Move tempNodes up one until tempNode becomes null, or the data is found
        while (tempNode != null && !tempNode.getData().equals(data))
        {
            previousNode = tempNode;
            tempNode = tempNode.getNext();
        }
        // If data was found
        if (tempNode != null)
        {
            // If node is last set tail to previous node
            if(tempNode.getNext() == null)
                tail = previousNode;
            
            // If data was found make previous node's next set to the found node's next
            // Set found nodes next to null
            previousNode.setNext(tempNode.getNext());
            tempNode.setNext(null);
            // Decrease size of linked list
            size--;
            return true;
        }
        // If nothing was found return false
        return false;
    }
    
    /**
     * Empties the linked list and removes head and tail pointers
     */
    public void empty()
    {
        // Manually unlink all nodes so garbage collection can get all in one go
        Node<TemplateType> tempNode = head;
        
        // Runs if list is not empty
        if (tempNode != null)
        {

            Node<TemplateType> tempNodeNext = tempNode.getNext();
            
            // Goes through all nodes
            while (tempNode != null)
            {
                // Sets next to null and moves a head to next element
                tempNode.setNext(null);
                tempNode = tempNodeNext;
                // If
                if (tempNodeNext != null)
                    tempNodeNext = tempNodeNext.getNext();
                // If Next node is null set tempNode to null to end loop
                else
                    tempNode = null;
            }
            
            // Clears head tail and size to 0
            this.head = null;
            this.tail = null;
            this.size = 0;
        }
    }

    /**
     * Looks through each node and looks at its data and compares it to argument.
     * If found returns true if not returns false.
     * @param data : value searching for in nodes
     * @return boolean : true if data was found, false if data not found
     */
    public boolean contains(TemplateType data)
    {
        Node<TemplateType> tempNode = head;
        
        // No nodes in linked list
        if (tempNode == null)
            return false;
        
        // Updates node until  it becomes null or finds the data
        while (tempNode != null && !tempNode.getData().equals(data))
        {
            tempNode = tempNode.getNext();
        }
        // Returns true if node was found
        if (tempNode != null)
        {
            return true;
        }
        // False if node wasn't found
        else 
            return false;
        
    }
    
    /**
     * Returns the first node in linked list
     * @return Node<TemplateType> set at head
     */
    public Node<TemplateType> getHead()
    {
        return head;
    }
    
    /**
     * Returns the last node in linked list
     * @return Node<TemplateType> set at tail
     */
    public Node<TemplateType> getTail()
    {
        return tail;
    }
    
    /**
     * Outputs all nodes separated by a " - ".
     */
    public String toString()
    {
        String listOfAllNodes = "";
        // Starts at index 0
        Node<TemplateType> tempNode = head;

        // If linked list is empty
        if (size == 0)
            return "None Remain";

        // Goes through each node in linked list adding value to output stop before last node
        while (tempNode.getNext() != null)
        {
            listOfAllNodes += tempNode.getData() + " - ";
            tempNode = tempNode.getNext();
        }
        // Adds last nodes data to output
        if (size != 0)
            listOfAllNodes += tempNode.getData();

        // Returns list of all nodes
        return listOfAllNodes; 
    }
    
}
