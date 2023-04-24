import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, TouchableOpacity, Image, FlatList } from 'react-native';
import { useRouter } from 'expo-router';
import styles from './welcome.style';
import { icons, SIZES } from '../../../constants';

const Welcome = () => {
  const router = useRouter();
  const [city, setCity] = useState('');
  const [aqiData, setAqiData] = useState(null);

  const fetchData = async () => {
    try {
      const response = await fetch(`https://c3bb-2a09-bac1-36a0-68-00-27d-a6.ngrok-free.app/aqi/${city}`, {
        mode: 'cors', // Add CORS mode option
      });
      const data = await response.json();
      setAqiData(data);
      console.log(data);
    } catch (error) {
      console.error(error);
    }
  };
  

  const handleCityChange = (text) => {
    setCity(text);
  };

  const handleSearch = () => {
    fetchData();
  };

  return (
    <View>
      <View style={styles.container}>
        <Text style={styles.userName}>Hello Aviral</Text>
        <Text style={styles.welcomeMessage}>Find your Cities AQI</Text>
      </View>

      <View style={styles.searchContainer}>
        <View style={styles.searchWrapper}>
          <TextInput
            multiline={true}
            style={styles.searchInput}
            value={city}
            onChangeText={handleCityChange}
            placeholder="Enter the name of the city!"
          />
        </View>
        <TouchableOpacity style={styles.searchBtn} onPress={handleSearch}>
          <Image
            source={icons.search}
            resizeMode="contain"
            style={styles.searchBtnImage}
          />
        </TouchableOpacity>
      </View>

      {aqiData && (
        <View style={styles.containers}>
        <View style={styles.box}>
          <Text style={styles.label}>AQI: {aqiData.pollutant_avg}</Text>
          <Text style={styles.label}>City: {aqiData.city}</Text>
          <Text style={styles.label}>AQI Station Name: {aqiData.station}</Text>
        </View>
      </View>
      
      
      )}
    </View>
  );
};

export default Welcome;
