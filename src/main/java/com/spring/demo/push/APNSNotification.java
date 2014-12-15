/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.push;

import java.util.ArrayList;
import java.util.List;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;

import org.json.JSONException;

/**
 * 이 클래스는...
 *
 * @author user Created on 2014. 8. 27.
 */

public class APNSNotification extends PushFoundation {

	private APNSNotification() {

	}

	/**
	 * Push a test notification to a device, given command-line parameters.
	 * 
	 * @param args
	 * @throws KeystoreException
	 * @throws CommunicationException
	 */
	private static void pushTest(String[] args) throws CommunicationException,
			KeystoreException {
		String keystore = args[0];
		String password = args[1];
		String token = args[2];
		boolean production = args.length >= 4 ? args[3]
				.equalsIgnoreCase("production") : false;
		boolean simulation = args.length >= 4 ? args[3]
				.equalsIgnoreCase("simulation") : false;
		boolean complex = args.length >= 5 ? args[4]
				.equalsIgnoreCase("complex") : false;
		boolean threads = args.length >= 5 ? args[4]
				.equalsIgnoreCase("threads") : false;
		int threadDevices = args.length >= 6 ? Integer.parseInt(args[5]) : 100;
		int threadThreads = args.length >= 7 ? Integer.parseInt(args[6]) : 10;
		boolean simple = !complex && !threads;

		verifyKeystore(keystore, password, production);

		if (simple) {

			/* Push a test alert */
			List<PushedNotification> notifications = Push.test(keystore,
					password, production, token);
			printPushedNotifications(notifications);

		} else if (complex) {

			/* Push a more complex payload */
			List<PushedNotification> notifications = Push.payload(
					createComplexPayload(), keystore, password, production,
					token);
			printPushedNotifications(notifications);

		} else if (threads) {

			/* Push a Hello World! alert repetitively using NotificationThreads */
			pushSimplePayloadUsingThreads(keystore, password, production,
					token, simulation, threadDevices, threadThreads);

		}
	}

	/**
	 * Create a complex payload for test purposes.
	 * 
	 * @return
	 */
	private static Payload createComplexPayload() {
		PushNotificationPayload complexPayload = PushNotificationPayload
				.complex();
		try {
			// You can use addBody to add simple message, but we'll use
			// a more complex alert message so let's comment it
			complexPayload.addCustomAlertBody("My alert message");
			complexPayload.addCustomAlertActionLocKey("Open App");
			complexPayload.addCustomAlertLocKey("javapns rocks %@ %@%@");
			ArrayList<Object> parameters = new ArrayList<>();
			parameters.add("Test1");
			parameters.add("Test");
			parameters.add(2);
			complexPayload.addCustomAlertLocArgs(parameters);
			complexPayload.addBadge(45);
			complexPayload.addSound("default");
			complexPayload.addCustomDictionary("acme", "foo");
			complexPayload.addCustomDictionary("acme2", 42);
			ArrayList<Object> values = new ArrayList<>();
			values.add("value1");
			values.add(2);
			complexPayload.addCustomDictionary("acme3", values);
		} catch (JSONException e) {
			System.out.println("Error creating complex payload:");
			e.printStackTrace();
		}
		return complexPayload;
	}

	protected static void pushSimplePayloadUsingThreads(String keystore,
			String password, boolean production, String token,
			boolean simulation, int devices, int threads) {
		try {

			System.out
					.println("Creating PushNotificationManager and AppleNotificationServer");
			AppleNotificationServer server = new AppleNotificationServerBasicImpl(
					keystore, password, production);
			System.out.println("Creating payload (simulation mode)");
			// Payload payload = PushNotificationPayload.alert("Hello World!");
			Payload payload = PushNotificationPayload.test();

			System.out.println("Generating " + devices + " fake devices");
			List<Device> deviceList = new ArrayList<>(devices);
			for (int i = 0; i < devices; i++) {
				String tokenToUse = token;
				if (tokenToUse == null || tokenToUse.length() != 64) {
					tokenToUse = "123456789012345678901234567890123456789012345678901234567"
							+ (1000000 + i);
				}
				deviceList.add(new BasicDevice(tokenToUse));
			}

			System.out.println("Creating " + threads + " notification threads");
			NotificationThreads work = new NotificationThreads(server,
					simulation ? payload.asSimulationOnly() : payload,
					deviceList, threads);
			// work.setMaxNotificationsPerConnection(10000);
			System.out.println("Linking notification work debugging listener");
			work.setListener(DEBUGGING_PROGRESS_LISTENER);

			System.out.println("Starting all threads...");
			long timestamp1 = System.currentTimeMillis();
			work.start();
			System.out.println("All threads started, waiting for them...");
			work.waitForAllThreads();
			long timestamp2 = System.currentTimeMillis();
			System.out.println("All threads finished in "
					+ (timestamp2 - timestamp1) + " milliseconds");

			printPushedNotifications(work.getPushedNotifications());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * A NotificationProgressListener you can use to debug NotificationThreads.
	 */
	public static final NotificationProgressListener DEBUGGING_PROGRESS_LISTENER = new NotificationProgressListener() {
		@Override
		public void eventThreadStarted(NotificationThread notificationThread) {
			System.out.println("   [EVENT]: thread #"
					+ notificationThread.getThreadNumber() + " started with "
					+ notificationThread.getDevices().size()
					+ " devices beginning at message id #"
					+ notificationThread.getFirstMessageIdentifier());
		}

		@Override
		public void eventThreadFinished(NotificationThread thread) {
			System.out.println("   [EVENT]: thread #"
					+ thread.getThreadNumber() + " finished: pushed messages #"
					+ thread.getFirstMessageIdentifier() + " to "
					+ thread.getLastMessageIdentifier() + " toward "
					+ thread.getDevices().size() + " devices");
		}

		@Override
		public void eventConnectionRestarted(NotificationThread thread) {
			System.out.println("   [EVENT]: connection restarted in thread #"
					+ thread.getThreadNumber() + " because it reached "
					+ thread.getMaxNotificationsPerConnection()
					+ " notifications per connection");
		}

		@Override
		public void eventAllThreadsStarted(
				NotificationThreads notificationThreads) {
			System.out.println("   [EVENT]: all threads started: "
					+ notificationThreads.getThreads().size());
		}

		@Override
		public void eventAllThreadsFinished(
				NotificationThreads notificationThreads) {
			System.out.println("   [EVENT]: all threads finished: "
					+ notificationThreads.getThreads().size());
		}

		@Override
		public void eventCriticalException(
				NotificationThread notificationThread, Exception exception) {
			System.out.println("   [EVENT]: critical exception occurred: "
					+ exception);
		}
	};

	/**
	 * Print to the console a comprehensive report of all pushed notifications
	 * and results.
	 * 
	 * @param notifications
	 *            a raw list of pushed notifications
	 */
	public static void printPushedNotifications(
			List<PushedNotification> notifications) {
		List<PushedNotification> failedNotifications = PushedNotification
				.findFailedNotifications(notifications);
		List<PushedNotification> successfulNotifications = PushedNotification
				.findSuccessfulNotifications(notifications);
		int failed = failedNotifications.size();
		int successful = successfulNotifications.size();

		if (successful > 0 && failed == 0) {
			printPushedNotifications("All notifications pushed successfully ("
					+ successfulNotifications.size() + "):",
					successfulNotifications);
		} else if (successful == 0 && failed > 0) {
			printPushedNotifications("All notifications failed ("
					+ failedNotifications.size() + "):", failedNotifications);
		} else if (successful == 0 && failed == 0) {
			System.out
					.println("No notifications could be sent, probably because of a critical error");
		} else {
			printPushedNotifications("Some notifications failed ("
					+ failedNotifications.size() + "):", failedNotifications);
			printPushedNotifications("Others succeeded ("
					+ successfulNotifications.size() + "):",
					successfulNotifications);
		}
	}

	/**
	 * Print to the console a list of pushed notifications.
	 * 
	 * @param description
	 *            a title for this list of notifications
	 * @param notifications
	 *            a list of pushed notifications to print
	 */
	public static void printPushedNotifications(String description,
			List<PushedNotification> notifications) {
		System.out.println(description);
		for (PushedNotification notification : notifications) {
			try {
				System.out.println("  " + notification.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
