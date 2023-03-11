/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Models.Hotel;

/**
 *
 * @author youssef
 */
public interface HotelInterface {
    //add
    public void addHotel(Hotel h);
    //liste : select
    public List<Hotel> getAllHotels() ;
    //getbyid: hotel
    public Hotel GetHotelById(int Id);
    //update hotel
    public void updateHotel(Hotel h);
    //delete hotel
    public void deleteHotel(int id);
    //Filter by name 
    public Hotel filterByName(String nom);
    //filter by adresse
    public Hotel filterByAdress(String adresse);
    
}
