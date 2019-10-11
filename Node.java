// Templated for extra credit
public class Node<TemplateType>
{
    // Node holds the data, and reference to next node in linked list, next is null if last item in list
    private TemplateType data;
    private Node<TemplateType> next;
    
    /**
     * Returns value(data) for the node
     * @return data
     */
    public TemplateType getData()
    {
        return this.data;
    }
    
    /**
     * Sets the value(data) for the node
     * @param data
     */
    public void setData(TemplateType data)
    {
        this.data = data;
    }
    
    /**
     * Returns the next node in linked list
     * @return The next Node<TemplateType> in linked list
     */
    public Node<TemplateType> getNext()
    {
        return this.next;
    }
    
    /**
     * Sets the next node
     * @param Node<TemplateType> to be next
     */
    public void setNext(Node<TemplateType> next)
    {
        this.next = next;
    }
    
}
