package calender_export_ms.controllers;


import calender_export_ms.models.*;
import calender_export_ms.services.CalendarExportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Data
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/users/{userId}/events")
public class CalendarExportController {

    private final CalendarExportService calendarExportService;

    @GetMapping(/*produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}*/)
    public ResponseEntity queryEvents(@PathVariable Long userId, @RequestParam String exportType, @RequestParam(required = false) Format format, @PageableDefault Pageable page){
        try {
            return calendarExportService.queryEvents(userId, exportType, format, page);
        } catch (JAXBException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
