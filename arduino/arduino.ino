// Konstansok
const float MIN_SPEED = 3.0; // Minimum sebesseg (km / h)
const float MAX_SPEED = 15.0; // Maximum sebesseg (km / h)
const float MIN_PRESSURE= 1.0; // Minimum nyomas (bar)
const float MAX_PRESSURE= 10.0; // Maximum nyomas (bar)
const long LOOP_DELAY = 10; // (ms)
const int HALL_SENSOR_THRESHOLD = 900; // Kb 2.5 Volt
const float PRESSURE_THRESHOLD = 0.1; // Bar

// Bemeneti adatok
float x; // Csavarok szama
float r; // Kerek sugara (cm)
float d; // Hozam (l / ha)
float a; // Duznik kozti tavolsag (m)
float debitAt1Bar = -1.0;
float debitAt2Bar = -1.0;
float debitAt3Bar = -1.0;
float debitAt4Bar = -1.0;
float debitAt5Bar = -1.0;
float debitAt6Bar = -1.0;
float debitAt7Bar = -1.0;
float debitAt8Bar = -1.0;

// Segedvaltozok
boolean isInitialized = false; // Megvan-e az osszes adat (telefonrol)
float v = 0.0; // Traktor sebesseg (km / h)

// Bemeneti pinek
const int HALL_SENSOR = A0; // Hall szenzor
const int PRESSURE_SENSOR = A1; // Nyomas szenzor

// Kimeneti pinek
const int MAIN_OPEN = 5; // Fo szelep nyito rele
const int MAIN_CLOSE = 6; // Fo szelep zaro rele
const int VALVE_1_OPEN= 7; // Szelep 1 nyito rele
const int VALVE_1_CLOSE= 8; // Szelep 1 zaro rele
const int VALVE_2_OPEN= 9; // Szelep 2 nyito rele
const int VALVE_2_CLOSE= 10; // Szelep 2 zaro rele
const int VALVE_3_OPEN= 11; // Szelep 3 nyito rele
const int VALVE_3_CLOSE= 12; // Szelep 3 zaro rele
const int VALVE_4_OPEN= 13; // Szelep 4 nyito rele
const int VALVE_4_CLOSE= 14; // Szelep 4 zaro rele
const int VALVE_5_OPEN= 15; // Szelep 5 nyito rele
const int VALVE_5_CLOSE= 16; // Szelep 5 zaro rele

void setup() {
    initializePins();
    setValvePinsToZero();
}

void loop() {
    if (isInitialized) {
        measureSpeed();
        adjustPressure();
    } else {
        hardcodeValues(); // Ideiglenes - az ertekek majd telefonrol fognak jonni
    }
    delay(LOOP_DELAY);
}

void initializePins() {
    Serial.begin(9600);
    pinMode(HALL_SENSOR, INPUT);
    pinMode(PRESSURE_SENSOR, INPUT);
    pinMode(MAIN_OPEN, OUTPUT);
    pinMode(MAIN_CLOSE, OUTPUT);
    pinMode(VALVE_1_OPEN, OUTPUT);
    pinMode(VALVE_1_CLOSE, OUTPUT);
    pinMode(VALVE_2_OPEN, OUTPUT);
    pinMode(VALVE_2_CLOSE, OUTPUT);
    pinMode(VALVE_3_OPEN, OUTPUT);
    pinMode(VALVE_3_CLOSE, OUTPUT);
    pinMode(VALVE_4_OPEN, OUTPUT);
    pinMode(VALVE_4_CLOSE, OUTPUT);
    pinMode(VALVE_5_OPEN, OUTPUT);
    pinMode(VALVE_5_CLOSE, OUTPUT);
}

void setValvePinsToZero() {
    digitalWrite(MAIN_OPEN, 0);
    digitalWrite(MAIN_CLOSE, 0);
    digitalWrite(VALVE_1_OPEN, 0);
    digitalWrite(VALVE_1_CLOSE, 0);
    digitalWrite(VALVE_2_OPEN, 0);
    digitalWrite(VALVE_2_CLOSE, 0);
    digitalWrite(VALVE_3_OPEN, 0);
    digitalWrite(VALVE_3_CLOSE, 0);
    digitalWrite(VALVE_4_OPEN, 0);
    digitalWrite(VALVE_4_CLOSE, 0);
    digitalWrite(VALVE_5_OPEN, 0);
    digitalWrite(VALVE_5_CLOSE, 0);
}

// Pelda ertekek, tesztelni
void hardcodeValues() {
    x = 6.0; // 6 csavar
    r = 80.0; // 80 cm kereksugar
    d = 300.0; // 300 l / ha hozam
    a = 0.5; // 50 cm tavolsag duznik kozott
    debitAt1Bar = -1.0;
    debitAt2Bar = -1.0;
    debitAt3Bar = 0.59;
    debitAt4Bar = 0.68;
    debitAt5Bar = 0.75;
    debitAt6Bar = 0.83;
    debitAt7Bar = 0.9;
    debitAt8Bar = -1.0;
    isInitialized = true;
}

boolean currentHallSensorOn;
boolean previousHallSensorOn = false;
float previousHallSensorOnTimestamp = 0.0;
float currentTimestamp;
float timeSinceLastHallSensorOn;
float rpm;
float vTemporary;
int hallSensorValue;

void measureSpeed() {
    hallSensorValue = analogRead(HALL_SENSOR);
    currentHallSensorOn = hallSensorValue > HALL_SENSOR_THRESHOLD;
    if (!previousHallSensorOn && currentHallSensorOn) {
        currentTimestamp = millis();
        timeSinceLastHallSensorOn = currentTimestamp - previousHallSensorOnTimestamp;
        previousHallSensorOnTimestamp = currentTimestamp;
        rpm = (60000.0 * x) / timeSinceLastHallSensorOn;
        vTemporary = rpm * r * 0.0037699;
        Serial.print("It's been ");
        Serial.print(timeSinceLastHallSensorOn);
        Serial.print(" ms. This means ");
        Serial.print(vTemporary);
        Serial.println(" km/h.");
        if (vTemporary > MIN_SPEED && vTemporary < MAX_SPEED) {
            v = vTemporary;
        }
    }
    previousHallSensorOn = currentHallSensorOn;
}

float nozzleDebit; // Duzninkenti nyomas, q (l / min)
float expectedPressure; // Bar
float currentPressure; // Bar

void adjustPressure() {
    nozzleDebit = calculateNozzleDebit();
    expectedPressure = calculateExpectedPressure(nozzleDebit);
    currentPressure = measurePressure();
    if (expectedPressure < MIN_PRESSURE || expectedPressure > MAX_PRESSURE || currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE) {
       // Hibauzenet telefonra (szenzorhiba / duzni out of range)
    } else {
       if (expectedPressure - currentPressure > PRESSURE_THRESHOLD) {
            digitalWrite(MAIN_OPEN, 1);
            digitalWrite(MAIN_CLOSE, 0);
       } else if (currentPressure - expectedPressure > PRESSURE_THRESHOLD) {
            digitalWrite(MAIN_OPEN, 0);
            digitalWrite(MAIN_CLOSE, 1);
       } else {
            digitalWrite(MAIN_OPEN, 0);
            digitalWrite(MAIN_CLOSE, 0);
       }
    }
}

float calculateNozzleDebit() {
    return (d * a * v) / 600.0;
}

float calculateExpectedPressure(float debit) {
    if (debit < debitAt1Bar) {
        return calculatePressureBetweenTwoPoints(debit, 0.0, debitAt1Bar, 0.0, 1.0);
    } else if (debit >= debitAt1Bar && debit < debitAt2Bar) {
        return calculatePressureBetweenTwoPoints(debit, debitAt1Bar, debitAt2Bar, 1.0, 2.0);
    } else if (debit >= debitAt2Bar && debit < debitAt3Bar) {
        return calculatePressureBetweenTwoPoints(debit, debitAt2Bar, debitAt3Bar, 2.0, 3.0);
    } else if (debit >= debitAt3Bar && debit < debitAt4Bar) {
        return calculatePressureBetweenTwoPoints(debit, debitAt3Bar, debitAt4Bar, 3.0, 4.0);
    } else if (debit >= debitAt4Bar && debit < debitAt5Bar) {
        return calculatePressureBetweenTwoPoints(debit, debitAt4Bar, debitAt5Bar, 4.0, 5.0);
    } else if (debit >= debitAt5Bar && debit < debitAt6Bar) {
        return calculatePressureBetweenTwoPoints(debit, debitAt5Bar, debitAt6Bar, 5.0, 6.0);
    } else if (debit >= debitAt6Bar && debit < debitAt7Bar) {
        return calculatePressureBetweenTwoPoints(debit, debitAt6Bar, debitAt7Bar, 6.0, 7.0);
    } else if (debit >= debitAt7Bar && debit < debitAt8Bar) {
        return calculatePressureBetweenTwoPoints(debit, debitAt7Bar, debitAt8Bar, 7.0, 8.0);
    } else {
        return -1.0;
    }
}

float calculatePressureBetweenTwoPoints(float debit, float debitMin, float debitMax, float pressureMin, float pressureMax) {
    if (debitMin == -1.0 || debitMax == -1.0) {
        return -1.0;
    } else {
        return (pressureMin * debit - pressureMax * debit - pressureMin * debitMax + pressureMax * debitMin) / (debitMin - debitMax);
    }
}

float pressureSensorVoltage;

float measurePressure() {
    pressureSensorVoltage = analogRead(PRESSURE_SENSOR) / 204.6;
    return (pressureSensorVoltage - 0.5) / 0.4;
}
