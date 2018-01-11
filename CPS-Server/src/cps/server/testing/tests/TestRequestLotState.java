package cps.server.testing.tests;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import cps.server.ServerController;
import cps.server.ServerException;
import cps.server.controllers.DatabaseController;
import cps.server.session.CustomerSession;
import cps.server.session.ServiceSession;
import cps.server.session.UserSession;
import cps.server.testing.utilities.CustomerData;
import cps.server.testing.utilities.ServerControllerTest;
import cps.api.request.*;
import cps.api.action.*;
import cps.api.response.*;
import cps.common.Constants;
import cps.common.Utilities.Pair;
import cps.entities.models.CarTransportation;
import cps.entities.models.Complaint;
import cps.entities.models.Customer;
import cps.entities.models.OnetimeService;
import cps.entities.models.ParkingLot;
import cps.entities.models.SubscriptionService;
import cps.entities.people.User;
import cps.server.ServerConfig;

import junit.framework.TestCase;
import org.junit.Test;

import com.google.gson.Gson;

import org.junit.Assert;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("unused")
public class TestRequestLotState extends ServerControllerTest {
	@Test
	public void testRequestLotState() throws ServerException {
		header("testRequestLotState");
		
		ServiceSession session = new ServiceSession();
		
		User user = session.login("malki", "1234");
		assertTrue(user == session.getUser());
		assertNotNull(session.getUser());
		
		ParkingLot lot = initParkingLot(session);
		
		RequestLotStateAction action = new RequestLotStateAction(user.getId(), lot.getId());
		printObject(action);
		
		ServerResponse response = server.dispatch(action, session);
		assertNotNull(response);
		assertTrue(response.success());
		printObject(response);
		assertThat(response, instanceOf(LotStateResponse.class));
		
		LotStateResponse specificResponse = (LotStateResponse) response;
		ParkingLot responseLot = specificResponse.getLot();
		assertNotNull(responseLot);
		assertEquals(lot.getId(), responseLot.getId());
		assertEquals(lot.getContent(), responseLot.getContent());
	}
}
