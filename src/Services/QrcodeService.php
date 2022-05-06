<?php
namespace App\Services;

use Endroid\QrCode\Builder\BuilderInterface;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;

class QrcodeService{

    /**
     * @var BuilderInterface
     */

    protected  $builder;

    public function __construct(BuilderInterface $builder)
    {
        $this->builder=$builder;

    }

    public function qrcode($query)
    {
            $url= "http://127.0.0.1:8000";

            $objDateTime = new \DateTime('NOW');
            $dateString = $objDateTime->format('d-m-Y H:i:s');

            $result= $this->builder

                ->data($url.$query)
                ->encoding(new Encoding('UTF-8'))
                ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
                ->size(200)
                ->margin(10)
                ->labelText($dateString)
                ->build()
            ;

            $namePng = uniqid('','').'.png';

            $result->saveToFile( (\dirname(__DIR__,2).'/public/assets/qr-code/'.$namePng) );

            return $result->getDataUri();
    }
}

?>