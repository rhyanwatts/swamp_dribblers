package BikeSack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BikeSack.BikeSack.INSTRUMENTS;
import javax.swing.JProgressBar;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.Console;
import java.awt.event.ActionEvent;

public class GuiDisplay extends Display {
	
	public JFrame frame = new JFrame("The Bike Sack");
	public JPanel contentPane = new JPanel();
	public JTextField txtOdometer;
	public JTextField txtTripMeter;
	public JTextField txtFuelUsage;	
	public JProgressBar indicatorLeft;
	public JProgressBar indicatorRight;
	public JButton btnLowBeam;
	public JButton btnHighBeam;

	/*
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void show(Map<INSTRUMENTS, Instrument> instruments) 
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		indicatorLeft = new JProgressBar();
		indicatorLeft.setForeground(new Color(255, 255, 0));
		indicatorLeft.setMaximum(255);
		indicatorLeft.setBounds(10, 11, 160, 50);
		contentPane.add(indicatorLeft);
		
		indicatorRight = new JProgressBar();
		indicatorRight.setForeground(new Color(255, 255, 0));
		indicatorRight.setMaximum(255);
		indicatorRight.setBounds(464, 11, 160, 50);
		contentPane.add(indicatorRight);
		
		JLabel lblLights = new JLabel("Lights");
		lblLights.setHorizontalAlignment(SwingConstants.CENTER);
		lblLights.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLights.setBounds(273, 11, 78, 39);
		contentPane.add(lblLights);
		
		btnHighBeam = new JButton("HIGH");
		btnHighBeam.setBackground(Color.white);
		btnHighBeam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHighBeam.setBounds(329, 61, 85, 40);
		btnHighBeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{				
				btnHighBeam.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if(btnHighBeam.getBackground() == Color.white)
						{
							btnHighBeam.setBackground(Color.yellow);
							btnLowBeam.setBackground(Color.white);
						}else
						{
							btnHighBeam.setBackground(Color.white);
							btnLowBeam.setBackground(Color.yellow);
						}
					}
				});
			}
		});
		contentPane.add(btnHighBeam);
		
		btnLowBeam = new JButton("LOW");
		btnLowBeam.setBackground(Color.white);
		btnLowBeam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLowBeam.setBounds(215, 61, 85, 40);
		btnLowBeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(btnLowBeam.getBackground() == Color.white)
				{
					btnLowBeam.setBackground(Color.yellow);
					btnHighBeam.setBackground(Color.white);
				}else
				{
					btnLowBeam.setBackground(Color.white);
					btnHighBeam.setBackground(Color.yellow);
				}
			}
		});
		
		contentPane.add(btnLowBeam);
		
		JLabel lblOdometer = new JLabel("Odometer");
		lblOdometer.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdometer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOdometer.setBounds(148, 119, 152, 28);
		contentPane.add(lblOdometer);
		
		txtOdometer = new JTextField();
		txtOdometer.setHorizontalAlignment(SwingConstants.RIGHT);
		txtOdometer.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtOdometer.setText("0 KM");
		txtOdometer.setEditable(false);
		txtOdometer.setBounds(327, 123, 152, 28);
		contentPane.add(txtOdometer);
		txtOdometer.setColumns(10);
		
		JLabel lblTripMeter = new JLabel("Trip Meter");
		lblTripMeter.setHorizontalAlignment(SwingConstants.CENTER);
		lblTripMeter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTripMeter.setBounds(150, 158, 152, 28);
		contentPane.add(lblTripMeter);
		
		txtTripMeter = new JTextField();
		txtTripMeter.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTripMeter.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTripMeter.setText("0 KM");
		txtTripMeter.setEditable(false);
		txtTripMeter.setColumns(10);
		txtTripMeter.setBounds(327, 162, 152, 28);
		contentPane.add(txtTripMeter);
		
		JLabel lblFuelUsage = new JLabel("Fuel Usage");
		lblFuelUsage.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuelUsage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuelUsage.setBounds(150, 197, 152, 28);
		contentPane.add(lblFuelUsage);
		
		txtFuelUsage = new JTextField();
		txtFuelUsage.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFuelUsage.setText("5L/100KM");
		txtFuelUsage.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtFuelUsage.setEditable(false);
		txtFuelUsage.setColumns(10);
		txtFuelUsage.setBounds(327, 201, 152, 28);
		contentPane.add(txtFuelUsage);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(255);
		progressBar.setForeground(Color.YELLOW);
		progressBar.setBounds(75, 268, 225, 50);
		contentPane.add(progressBar);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setMaximum(255);
		progressBar_1.setForeground(Color.YELLOW);
		progressBar_1.setBounds(329, 268, 225, 50);
		contentPane.add(progressBar_1);
		
		JLabel lblFuel = new JLabel("Fuel");
		lblFuel.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuel.setBounds(105, 236, 152, 28);
		contentPane.add(lblFuel);
		
		JLabel lblTemp = new JLabel("Temperature");
		lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTemp.setBounds(362, 236, 152, 28);
		contentPane.add(lblTemp);
		frame.setVisible(true);
		
	}
	
	//Change background colour of Head lights 
	public void setHeadLight(JButton light, boolean on)
	{	
		if(on)
			light.setBackground(Color.yellow);
		else
			light.setBackground(Color.WHITE);
	}
	
	
	//Indicator loop
	public void indicatorFadeRight(JProgressBar indicator)
	{		
		for(int i = 0; i < indicator.getMaximum(); i++)
		{
			indicator.setValue(i);
			try 
			{
				Thread.sleep(5);
			} 
			catch (InterruptedException e) 
			{

			}
		}			
	}
	public void indicatorFadeLeft(JProgressBar indicator)
	{
		for(int i = 255; i > indicator.getMinimum(); i--)
		{
			indicator.setValue(i);
			try 
			{
				Thread.sleep(5);
			} 
			catch (InterruptedException e) 
			{

			}
		}
	}
	
}
