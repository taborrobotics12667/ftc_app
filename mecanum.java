    protected void holonomicDrive(double x, double y, double rotation)
    {
        double wheelPowers[] = new double[4];
        wheelPowers[0] = x + y + rotation;
        wheelPowers[1] = -x + y - rotation;
        wheelPowers[2] = -x + y + rotation;
        wheelPowers[3] = x + y - rotation;

        normalize(wheelPowers);

        leftFrontMotor.set(wheelPowers[0]);
        rightFrontMotor.set(wheelPowers[1]);
        leftRearMotor.set(wheelPowers[2]);
        rightRearMotor.set(wheelPowers[3]);
    }   //holonomicDrive

    public void normalize(double[] nums)
    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < wheelSpeeds.length; i++)
        {
            double magnitude = Math.abs(wheelSpeeds[i]);
            if (magnitude > maxMagnitude)
            {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0)
        {
            for (int i = 0; i < wheelSpeeds.length; i++)
            {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }   //normalizeInPlace