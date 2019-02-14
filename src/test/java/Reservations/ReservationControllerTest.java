package Reservations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReservationControllerTest {

    @InjectMocks
    private ReservationController reservationController;

    private MockMvc mockMvc;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void setup() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    public void testGet() throws Exception {


        mockMvc.perform(get("/reservations/777")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(111)));

    }

    @Test
    public void testPost() throws Exception {

        List<Long> a = new ArrayList<Long>();
        a.add(12L);
        a.add(777L);
        a.add(111L);

        Reservation postreservation = new Reservation(111L, 111L, a);

        String jsonRequest = om.writeValueAsString(postreservation);
        //System.out.println("HERE >>>"  + " <<< HERE");
        MvcResult result = mockMvc.perform(post("/reservations").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Reservation reservation = om.readValue(resultContent, Reservation.class);
        System.out.println("HERE >>>" + reservation.getId() + " <<< HERE");

    }



}