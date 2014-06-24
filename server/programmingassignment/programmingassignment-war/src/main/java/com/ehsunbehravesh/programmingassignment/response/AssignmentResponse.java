package com.ehsunbehravesh.programmingassignment.response;

import com.ehsunbehravesh.programmingassignment.entity.Assignment;
import java.util.List;

/**
 *
 * @author Ehsun Behravesh
 */
public class AssignmentResponse extends ServiceResponse {
    private Assignment assignment;
    
    private List<Assignment> assignments;

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
    
    
}
