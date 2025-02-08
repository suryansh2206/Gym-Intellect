package com.KnowIt.Gym_intellect_Crud.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.KnowIt.Gym_intellect_Crud.Entity.MemberPlanJoin;
import com.KnowIt.Gym_intellect_Crud.Services.MemberPlanJoinService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memberPlanJoins")
public class MemberPlanJoinController {

    @Autowired
    private MemberPlanJoinService memberPlanJoinService;

    // Get all member plan joins
    @GetMapping
    public ResponseEntity<List<MemberPlanJoin>> getAllMemberPlanJoins() {
        List<MemberPlanJoin> joins = memberPlanJoinService.getAllMemberPlanJoins();
        return new ResponseEntity<>(joins, HttpStatus.OK);
    }

    // Save a member plan join
    @PostMapping
    public ResponseEntity<MemberPlanJoin> saveMemberPlanJoin(@RequestBody MemberPlanJoin memberPlanJoin) {
        MemberPlanJoin savedJoin = memberPlanJoinService.saveMemberPlanJoin(memberPlanJoin);
        return new ResponseEntity<>(savedJoin, HttpStatus.CREATED);
    }

    // Update a member plan join
    @PutMapping("/{id}")
    public ResponseEntity<MemberPlanJoin> updateMemberPlanJoin(@PathVariable int id, @RequestBody MemberPlanJoin memberPlanJoin) {
        MemberPlanJoin updatedJoin = memberPlanJoinService.updateMemberPlanJoin(id, memberPlanJoin);
        return new ResponseEntity<>(updatedJoin, HttpStatus.OK);
    }

    // Delete a member plan join
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberPlanJoin(@PathVariable int id) {
        memberPlanJoinService.deleteMemberPlanJoin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get a member plan join by ID
    @GetMapping("/{id}")
    public ResponseEntity<MemberPlanJoin> getMemberPlanJoinById(@PathVariable int id) {
        Optional<MemberPlanJoin> join = memberPlanJoinService.getMemberPlanJoinById(id);
        return join.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}