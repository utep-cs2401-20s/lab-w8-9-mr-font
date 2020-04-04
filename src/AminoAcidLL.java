class AminoAcidLL {
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL head;


  //Setters
  //and
  //Getters


  AminoAcidLL() {

  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon) {

    this.aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);

    this.codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);

    this.counts = aminoAcidCodonCount(inCodon, this.codons);

    AminoAcidLL next = null;

  }

  AminoAcidLL(String inCodon, AminoAcidLL next) {

    this.aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);

    this.codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);

    this.counts = aminoAcidCodonCount(inCodon, this.codons);

    this.next = next;

  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops,
   * if not passes the task to the next node.
   * If there is no next node, add a new node to the list that would contain the codon.
   */
  private void addCodon(String inCodon) {


    //base case 1
    if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)) {

     // incrCodons(inCodon);

      for (int i = 0; i < codons.length; i++){
        if (codons[i].equalsIgnoreCase(inCodon)){
          counts[i]++;
        }
      }
    } else if (next != null) {

      next.addCodon(inCodon);
    } else {

      new AminoAcidLL(inCodon);
    }

    //base case 2
    if (next == null) {
      new AminoAcidLL(inCodon);
    }
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount() {

    
    int count = 0;

    for (int i = 0; i < this.counts.length; i++){
      count += this.counts[i];
    }


    return count;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
   *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList) {
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
   *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList) {
    int diff = 0;
    for (int i = 0; i < codons.length; i++) {
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts.
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList) {
    return 0;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList) {
    return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList() {
    return new char[]{};
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts() {
    return new int[]{};
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted() {
    return false;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence) {

    String inCodon = "";
    String workingStr = inSequence;

    while (workingStr.length() > 0){
      inCodon = workingStr.substring(0,2);
      addCodon(inCodon);
    }

    return null;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList) {

    AminoAcidLL sortedList = new AminoAcidLL();
    AminoAcidLL current = inList;

    boolean sorted = false;
    boolean moved = false

    if(!sorted){
      while(current.next != null){

        if(inList.aminoAcid > inList.next.aminoAcid){
          AminoAcidLL temp = inList.next;
          AminoAcidLL tempNext = inList.next.next;
          inList.next.next = inList;
          inList.next = tempNext;

          moved = true;
          inList = inList.next;
        }
        if(!moved){
          sorted = true;
        }
      }
    }

    return inList;
  }


//Helper methods:


  //todo: Test case
  public int[] aminoAcidCodonCount(String inCodon, String[] codons) {

    int[] codonCount = new int[codons.length];
    for (int i = 0; i < codons.length; i++) {
      if (codons[i].equalsIgnoreCase(inCodon)) {
        codonCount[i]++;
      }
    }
    return codonCount;
  }

  //public void incrCodons(String inCodon){

   // for (int i = 0; i < counts.length; i++){
     // if(){}

   // }
 // }
}