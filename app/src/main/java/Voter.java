public class Voter {

    String voter_id;
    String nid_number;
    String election_area;
    String has_voted;

    public Voter(String voter_id, String nid_number, String election_area, String has_voted) {
        this.voter_id = voter_id;
        this.nid_number = nid_number;
        this.election_area = election_area;
        this.has_voted = has_voted;
    }
}