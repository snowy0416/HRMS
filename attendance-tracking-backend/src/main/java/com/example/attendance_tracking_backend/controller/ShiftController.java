package com.example.attendance_tracking_backend.controller;

import com.example.attendance_tracking_backend.model.Shift;
import com.example.attendance_tracking_backend.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping
    public List<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shift> getShiftById(@PathVariable Long id) {
        Optional<Shift> shift = shiftService.getShiftById(id);
        return shift.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Shift createShift(@RequestBody Shift shift) {
        return shiftService.saveShift(shift);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shift> updateShift(@PathVariable Long id, @RequestBody Shift shiftDetails) {
        Optional<Shift> shift = shiftService.getShiftById(id);
        if (shift.isPresent()) {
            Shift updatedShift = shift.get();
            updatedShift.setName(shiftDetails.getName());
            updatedShift.setStartTime(shiftDetails.getStartTime());
            updatedShift.setEndTime(shiftDetails.getEndTime());
            return ResponseEntity.ok(shiftService.saveShift(updatedShift));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Long id) {
        if (shiftService.getShiftById(id).isPresent()) {
            shiftService.deleteShift(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

